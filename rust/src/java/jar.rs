use std::{
    fs::File,
    io::Read,
    path::{Path, PathBuf},
};

use anyhow::Result;
use glob::glob;
use zip::ZipArchive;

use crate::config::{paper::PAPER_PLUGIN_CONFIG, spigot::SPIGOT_PLUGIN_CONFIG};

pub fn discover_jar_files(plugin_folder: &PathBuf) -> Vec<PathBuf> {
    let pattern = format!("{}/**/*.jar", plugin_folder.to_string_lossy());
    let mut entries = Vec::new();

    for entry in glob(&pattern).expect("Failed to read glob pattern") {
        match entry {
            Ok(inner_path) => match inner_path.canonicalize() {
                Ok(path) => entries.push(path),
                Err(e) => log::error!("Failed to convert path to string: {:?}", e),
            },
            Err(e) => log::error!("Failed to canonicalize path: {:?}", e),
        }
    }

    entries
}

pub fn read_configs_from_jar<P: AsRef<Path>>(
    jar_path: P,
) -> Result<(Option<String>, Option<String>)> {
    let file = File::open(jar_path.as_ref())?;
    let mut archive = ZipArchive::new(file)?;

    let paper_plugin_yml = match archive.by_name(PAPER_PLUGIN_CONFIG).ok() {
        Some(mut file) => {
            let mut content = String::new();
            file.read_to_string(&mut content)?;
            Some(content)
        }
        None => None,
    };

    let spigot_plugin_yml = match archive.by_name(SPIGOT_PLUGIN_CONFIG).ok() {
        Some(mut file) => {
            let mut content = String::new();
            file.read_to_string(&mut content)?;
            Some(content)
        }
        None => None,
    };

    Ok((paper_plugin_yml, spigot_plugin_yml))
}
