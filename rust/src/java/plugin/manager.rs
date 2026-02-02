use std::{
    collections::HashMap,
    path::{Path, PathBuf},
    sync::Arc,
};

use anyhow::Result;
use j4rs::{Instance, InvocationArg, Jvm};
use pumpkin::plugin::Context;
use tokio::sync::mpsc;

use crate::{
    config::{
        paper::PaperPluginYml,
        spigot::{Command, SpigotPluginYml},
    },
    java::{jvm::commands::JvmCommand, plugin::command_manager::CommandManager},
};

#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum PluginState {
    /// Plugin config has been loaded, but not yet initialized in JVM
    Registered,
    /// Plugin class has been loaded and instance created
    Loaded,
    /// Plugin is enabled (onEnable called)
    Enabled,
    /// Plugin is disabled (onDisable called)
    Disabled,
    /// Plugin failed to load or enable
    Errored,
}

#[derive(Debug)]
pub enum PluginType {
    Paper(PaperPluginData),
    Spigot(SpigotPluginData),
}

#[derive(Debug)]
pub struct PaperPluginData {
    pub paper_config: PaperPluginYml,
    pub spigot_config: Option<SpigotPluginYml>,
}

#[derive(Debug)]
pub struct SpigotPluginData {
    pub spigot_config: SpigotPluginYml,
}

pub struct Plugin {
    /// Unique plugin name
    pub name: String,
    /// Plugin version
    pub version: String,
    /// Main class fully qualified name
    pub main_class: String,
    /// Path to the JAR file
    pub path: PathBuf,
    /// Plugin-specific data (Paper or Spigot)
    pub plugin_type: PluginType,
    /// Current state
    pub state: PluginState,
    /// Data folder for this plugin
    pub data_folder: PathBuf,
    pub instance: Option<Instance>,
    // The registered commands
    pub commands: HashMap<String, Command>,

    pub listeners: HashMap<String, Instance>,
}

pub struct PluginManager {
    pub plugins: HashMap<String, Plugin>,
}

impl PluginManager {
    pub fn new() -> Self {
        Self {
            plugins: HashMap::new(),
        }
    }

    pub fn add_plugin(&mut self, plugin: Plugin) {
        self.plugins.insert(plugin.name.clone(), plugin);
    }

    pub fn load_paper_plugin<P: AsRef<Path>>(
        &mut self,
        jar_path: P,
        paper_plugin_config: &str,
        spigot_plugin_config: &Option<String>,
    ) -> Result<()> {
        let parsed_paper_plugin = PaperPluginYml::from_str(paper_plugin_config)?;
        let parsed_spigot_plugin = match spigot_plugin_config {
            Some(config) => Some(SpigotPluginYml::from_str(config)?),
            None => None,
        };

        let plugin = Plugin {
            name: parsed_paper_plugin.name.clone(),
            version: parsed_paper_plugin.version.clone(),
            main_class: parsed_paper_plugin.main.clone(),
            plugin_type: PluginType::Paper(PaperPluginData {
                paper_config: parsed_paper_plugin,
                spigot_config: parsed_spigot_plugin,
            }),
            state: PluginState::Registered,
            data_folder: jar_path.as_ref().parent().unwrap().join("data"),
            path: jar_path.as_ref().to_path_buf(),
            instance: None,
            commands: HashMap::new(),
            listeners: HashMap::new(),
        };

        self.add_plugin(plugin);
        Ok(())
    }

    pub fn load_spigot_plugin<P: AsRef<Path>>(
        &mut self,
        jar_path: P,
        spigot_plugin_config: &str,
    ) -> Result<()> {
        let parsed_spigot_plugin = SpigotPluginYml::from_str(spigot_plugin_config)?;

        let plugin = Plugin {
            name: parsed_spigot_plugin.name.clone(),
            version: parsed_spigot_plugin.version.clone(),
            main_class: parsed_spigot_plugin.main.clone(),
            plugin_type: PluginType::Spigot(SpigotPluginData {
                spigot_config: parsed_spigot_plugin.clone(),
            }),
            state: PluginState::Registered,
            data_folder: jar_path.as_ref().parent().unwrap().join("data"),
            path: jar_path.as_ref().to_path_buf(),
            instance: None,
            commands: parsed_spigot_plugin.commands.unwrap_or(HashMap::new()),
            listeners: HashMap::new(),
        };

        self.add_plugin(plugin);
        Ok(())
    }

    pub fn enable_all_plugins(&mut self, jvm: &Jvm) -> Result<()> {
        // IMPORANT: enable trough PluginManager not manually
        let plugin_manager = jvm.invoke_static(
            "org.bukkit.Bukkit",
            "getPluginManager",
            InvocationArg::empty(),
        )?;
        for (_plugin_name, plugin) in &mut self.plugins {
            let plugin_instance = plugin.instance.as_ref().unwrap();
            let plugin_instance = jvm.clone_instance(&plugin_instance).unwrap();

            let result = jvm.invoke(
                &plugin_manager,
                "enablePlugin",
                &[InvocationArg::from(plugin_instance)],
            );

            match result {
                Ok(_) => {
                    plugin.state = PluginState::Enabled;
                    log::info!("Enabled PatchBukkit plugin: {}", plugin.name);
                }
                Err(e) => {
                    plugin.state = PluginState::Errored;
                    log::error!(
                        "Failed to enable PatchBukkit plugin {}: {:?}",
                        plugin.name,
                        e
                    );
                }
            }
        }
        Ok(())
    }

    pub fn disable_all_plugins(&mut self, jvm: &Jvm) -> Result<()> {
        let plugin_manager = jvm.invoke_static(
            "org.bukkit.Bukkit",
            "getPluginManager",
            InvocationArg::empty(),
        )?;
        for (_plugin_name, plugin) in &mut self.plugins {
            let plugin_instance = plugin.instance.as_ref().unwrap();
            let plugin_instance = jvm.clone_instance(&plugin_instance).unwrap();

            let result = jvm.invoke(
                &plugin_manager,
                "disablePlugin",
                &[InvocationArg::from(plugin_instance)],
            );

            match result {
                Ok(_) => {
                    plugin.state = PluginState::Disabled;
                    log::info!("Disabled PatchBukkit plugin: {}", plugin.name);
                }
                Err(e) => {
                    plugin.state = PluginState::Disabled;
                    log::error!(
                        "Failed to disable PatchBukkit plugin {}: {:?}",
                        plugin.name,
                        e
                    );
                }
            }
        }
        Ok(())
    }

    pub async fn instantiate_all_plugins(
        &mut self,
        jvm: &Jvm,
        server: &Arc<Context>,
        command_tx: mpsc::Sender<JvmCommand>,
        command_manager: &mut CommandManager,
    ) -> Result<()> {
        for (_plugin_name, plugin) in &mut self.plugins {
            let plugin_instance = jvm.invoke_static(
                "org.patchbukkit.loader.PatchBukkitPluginLoader",
                "createPlugin",
                &[
                    InvocationArg::try_from(&plugin.path.to_string_lossy().to_string())?,
                    InvocationArg::try_from(&plugin.main_class)?,
                ],
            )?;

            plugin.instance = Some(plugin_instance);

            for (cmd_name, cmd_data) in &plugin.commands {
                match command_manager
                    .register_command(
                        &jvm,
                        server,
                        &plugin,
                        cmd_name.clone(),
                        cmd_data,
                        command_tx.clone(),
                    )
                    .await
                {
                    Ok(_) => (),
                    Err(e) => {
                        log::error!(
                            "Failed to register command {} for plugin {}: {:?}",
                            cmd_name,
                            plugin.name,
                            e
                        );
                    }
                }
            }

            plugin.state = PluginState::Loaded;
            log::info!("Loaded and registered commands for: {}", plugin.name);
        }
        Ok(())
    }

    pub fn unload_all_plugins(&mut self) -> Result<()> {
        self.plugins.clear();
        Ok(())
    }
}
