use std::ffi::c_char;

use crate::java::native_callbacks::{CALLBACK_CONTEXT, utils::get_string};

#[repr(C)]
pub struct Vec3FFI {
    pub x: f64,
    pub y: f64,
    pub z: f64,
}

pub extern "C" fn rust_get_location(uuid_ptr: *const c_char, out: *mut Vec3FFI) -> bool {
    let uuid_str = get_string(uuid_ptr);
    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let uuid = uuid::Uuid::parse_str(&uuid_str).unwrap();
        let player = ctx.plugin_context.server.get_player_by_uuid(uuid);
        if let Some(player) = player {
            let position =
                tokio::task::block_in_place(|| ctx.runtime.block_on(async { player.position() }));

            unsafe {
                (*out).x = position.x;
                (*out).y = position.y;
                (*out).z = position.z;
            }

            return true;
        }
    }

    false
}
