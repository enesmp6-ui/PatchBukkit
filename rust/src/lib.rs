use std::sync::Arc;

use j4rs::ClasspathEntry;
use pumpkin::plugin::Context;
use pumpkin_api_macros::{plugin_impl, plugin_method};

pub mod config;
pub mod directories;
pub mod java;
pub mod plugin;

use directories::setup_directories;
use java::{
    jar::discover_jar_files,
    jvm::{initialize_jvm, setup_patchbukkit_server},
    resources::{cleanup_stale_files, sync_embedded_resources},
};

use crate::{java::jar::read_configs_from_jar, plugin::manager::PluginManager};

async fn on_load_inner(_plugin: &mut MyPlugin, server: Arc<Context>) -> Result<(), String> {
    server.init_log();
    log::info!("Starting PatchBukkit");

    // Setup directories
    let dirs = setup_directories(&server)?;

    let mut plugin_manager = PluginManager::new();

    // Discover and prepare JAR files
    let jar_paths = discover_jar_files(&dirs.plugins);
    for jar_path in &jar_paths {
        match read_configs_from_jar(jar_path) {
            Ok(configs) => match configs {
                (Some(paper_plugin_config), spigot @ _) => {
                    match plugin_manager.load_paper_plugin(jar_path, &paper_plugin_config, &spigot)
                    {
                        Ok(_) => log::info!("Loaded Paper plugin from JAR: {}", jar_path.display()),
                        Err(err) => log::error!("Failed to load Paper plugin from JAR: {}", err),
                    }
                }
                (None, Some(spigot)) => {
                    match plugin_manager.load_spigot_plugin(jar_path, &spigot) {
                        Ok(_) => {
                            log::info!("Loaded Spigot plugin from JAR: {}", jar_path.display())
                        }
                        Err(err) => log::error!("Failed to load Spigot plugin from JAR: {}", err),
                    }
                }
                (None, None) => log::warn!(
                    "Could not find any plugin configuration in JAR: {}",
                    jar_path.display()
                ),
            },
            Err(err) => {
                log::error!(
                    "Failed to read configs from PatchBukkit plugin jar: {}",
                    err
                );
            }
        }
    }
    let classpath_entries = jar_paths
        .iter()
        .map(|entry| ClasspathEntry::new(entry))
        .collect();

    // Manage embedded resources
    cleanup_stale_files(&dirs.j4rs);
    sync_embedded_resources(&dirs.j4rs)?;

    // Initialize JVM and PatchBukkit server
    let jvm = initialize_jvm(classpath_entries, &dirs.j4rs)?;
    setup_patchbukkit_server(&jvm)?;

    plugin_manager
        .load_all_plugins(&jvm)
        .map_err(|err| format!("Failed to load PatchBukkit plugins: {}", err))?;

    plugin_manager
        .enable_all_plugins(&jvm)
        .map_err(|err| format!("Failed to enable PatchBukkit plugins: {}", err))?;

    Ok(())
}

#[plugin_method]
async fn on_load(&mut self, server: Arc<Context>) -> Result<(), String> {
    on_load_inner(self, server).await
}

#[plugin_impl]
pub struct MyPlugin {}

impl MyPlugin {
    pub fn new() -> Self {
        MyPlugin {}
    }
}

impl Default for MyPlugin {
    fn default() -> Self {
        Self::new()
    }
}
