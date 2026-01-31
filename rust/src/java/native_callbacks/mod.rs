use std::{
    ffi::{CStr, c_char, c_void},
    sync::{Arc, OnceLock},
};

use anyhow::Result;
use j4rs::{InvocationArg, Jvm};
use pumpkin::plugin::Context;
use pumpkin_util::text::TextComponent;

static CALLBACK_CONTEXT: OnceLock<CallbackContext> = OnceLock::new();

struct CallbackContext {
    pub plugin_context: Arc<Context>,
    pub runtime: tokio::runtime::Handle,
}

pub fn init_callback_context(
    plugin_context: Arc<Context>,
    runtime: tokio::runtime::Handle,
) -> Result<()> {
    let context = CallbackContext {
        plugin_context,
        runtime,
    };

    CALLBACK_CONTEXT
        .set(context)
        .map_err(|_| anyhow::anyhow!("Failed to set callback context"))?;
    Ok(())
}

pub fn initialize_callbacks(jvm: &Jvm) -> Result<()> {
    let send_message_addr = rust_send_message as *const () as i64;
    let register_event_addr = rust_register_event as *const () as i64;

    jvm.invoke_static(
        "org.patchbukkit.bridge.NativePatchBukkit",
        "initCallbacks",
        &[
            InvocationArg::try_from(send_message_addr)?.into_primitive()?,
            InvocationArg::try_from(register_event_addr)?.into_primitive()?,
        ],
    )?;

    Ok(())
}

#[unsafe(no_mangle)]
pub extern "C" fn rust_send_message(uuid_ptr: *const c_char, message_ptr: *const c_char) {
    // This into_owned might not seem to make any sense, but it's necessary to ensure that the string is owned by the Rust code and can move into the runtime.
    let uuid_str = unsafe { CStr::from_ptr(uuid_ptr).to_string_lossy().into_owned() };
    let message = unsafe { CStr::from_ptr(message_ptr).to_string_lossy().into_owned() };

    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let uuid = uuid::Uuid::parse_str(&uuid_str).unwrap();

        ctx.runtime.spawn(async move {
            let player = ctx.plugin_context.server.get_player_by_uuid(uuid);
            if let Some(player) = player {
                player
                    .send_system_message(&TextComponent::from_legacy_string(&message))
                    .await;
            }
        });
    }
}

#[unsafe(no_mangle)]
pub extern "C" fn rust_register_event(
    listener_ptr: *const c_void, // opaque pointer to Java object
    plugin_ptr: *const c_void,
) {
    // Handle event registration
}
