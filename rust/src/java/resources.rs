use std::{collections::HashSet, fs, path::PathBuf};

use rust_embed::Embed;

#[derive(Embed)]
#[folder = "resources/"]
pub struct Resources;

pub fn cleanup_stale_files(j4rs_folder: &PathBuf) {
    let allowed: HashSet<String> = Resources::iter().map(|p| p.to_string()).collect();

    for entry in walkdir::WalkDir::new(j4rs_folder)
        .into_iter()
        .filter_map(|e| e.ok())
        .filter(|e| e.file_type().is_file())
    {
        let rel_path = entry
            .path()
            .strip_prefix(j4rs_folder)
            .unwrap()
            .to_string_lossy()
            .to_string();

        if !allowed.contains(&rel_path) {
            log::warn!("Removing stale j4rs file: {rel_path}");
            let _ = fs::remove_file(entry.path());
        }
    }
}

pub fn sync_embedded_resources(j4rs_folder: &PathBuf) -> Result<(), String> {
    for resource_path_str in Resources::iter() {
        let resource_path = j4rs_folder.join(resource_path_str.to_string());
        let resource = Resources::get(&resource_path_str).unwrap();

        if !resource_path.exists() {
            write_resource(&resource_path, &resource.data)?;
        } else {
            update_resource_if_changed(&resource_path, &resource.data)?;
        }
    }

    Ok(())
}

fn write_resource(path: &PathBuf, data: &[u8]) -> Result<(), String> {
    let parent = path.parent().ok_or("Resource has no parent directory")?;
    fs::create_dir_all(parent)
        .map_err(|err| format!("Failed to create parent for resource: {:?}", err))?;

    fs::write(path, data).map_err(|err| format!("Failed to add resource: {:?}", err))
}

fn update_resource_if_changed(path: &PathBuf, new_data: &[u8]) -> Result<(), String> {
    let old_data = fs::read(path).map_err(|err| format!("Failed to read resource: {:?}", err))?;

    if new_data != old_data {
        fs::write(path, new_data).map_err(|err| format!("Failed to update resource: {:?}", err))?;
    }

    Ok(())
}
