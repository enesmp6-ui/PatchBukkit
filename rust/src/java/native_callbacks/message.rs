use std::ffi::{CStr, c_char};

use pumpkin_util::text::TextComponent;

use crate::java::native_callbacks::CALLBACK_CONTEXT;

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
