use std::ffi::c_char;

use pumpkin_util::text::TextComponent;

use crate::java::native_callbacks::{CALLBACK_CONTEXT, utils::get_string};

pub extern "C" fn rust_send_message(uuid_ptr: *const c_char, message_ptr: *const c_char) {
    let uuid_str = get_string(uuid_ptr);
    let message = get_string(message_ptr);

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
