use std::path::PathBuf;

use j4rs::{ClasspathEntry, InvocationArg, Jvm, JvmBuilder};

pub fn initialize_jvm(
    classpath_entries: Vec<ClasspathEntry>,
    j4rs_folder: &PathBuf,
) -> Result<Jvm, String> {
    let jvm = JvmBuilder::new()
        .classpath_entries(classpath_entries)
        .with_base_path(j4rs_folder)
        .build()
        .map_err(|err| format!("JVM failed to init: {:?}", err))?;

    Ok(jvm)
}

pub fn setup_patchbukkit_server(jvm: &Jvm) -> Result<(), String> {
    let patchbukkit_server = jvm
        .create_instance("org.patchbukkit.PatchBukkitServer", InvocationArg::empty())
        .map_err(|err| format!("Failed to create PatchBukkitServer instance: {:?}", err))?;

    jvm.invoke_static(
        "org.bukkit.Bukkit",
        "setServer",
        &[InvocationArg::from(patchbukkit_server)],
    )
    .map_err(|err| format!("Failed to set Bukkit server: {:?}", err))?;

    Ok(())
}
