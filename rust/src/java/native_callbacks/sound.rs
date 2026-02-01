use std::ffi::{c_char, c_double, c_float};

use pumpkin::command::args::entities::{EntitySelectorType, TargetSelector};
use pumpkin_data::sound::{Sound, SoundCategory};
use pumpkin_protocol::{IdOr, java::client::play::CEntitySoundEffect};
use pumpkin_util::math::vector3::Vector3;
use rand::{RngExt, rng};

use crate::java::native_callbacks::{CALLBACK_CONTEXT, utils::get_string};

pub extern "C" fn rust_player_entity_play_sound(
    player_uuid_ptr: *const c_char,
    sound_name_ptr: *const c_char,
    sound_category_ptr: *const c_char,
    entity_uuid_ptr: *const c_char,
    volume: c_float,
    pitch: c_float,
) {
    let player_uuid_str = get_string(player_uuid_ptr);
    let sound_name = get_string(sound_name_ptr);
    let sound_category = get_string(sound_category_ptr);
    let entity_uuid_str = get_string(entity_uuid_ptr);

    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let player_uuid = uuid::Uuid::parse_str(&player_uuid_str).unwrap();
        let entity_uuid = uuid::Uuid::parse_str(&entity_uuid_str).unwrap();

        ctx.runtime.spawn(async move {
            let player = ctx.plugin_context.server.get_player_by_uuid(player_uuid);

            let entity = ctx.plugin_context.server.select_entities(
                &TargetSelector::new(EntitySelectorType::Uuid(entity_uuid)),
                None,
            );
            if entity.len() != 1 {
                return;
            }

            let entity = entity.first().unwrap().get_entity();

            if let Some(player) = player {
                let sound = match Sound::from_name(&sound_name) {
                    Some(sound) => sound,
                    None => return,
                };

                let category = match SoundCategory::from_name(&sound_category) {
                    Some(category) => category,
                    None => return,
                };

                let seed = rng().random::<i64>();

                player
                    .client
                    .enqueue_packet(&CEntitySoundEffect::new(
                        IdOr::Id(sound as u16),
                        category,
                        entity.entity_id.into(),
                        volume,
                        pitch,
                        seed,
                    ))
                    .await;
            }
        });
    }
}

pub extern "C" fn rust_player_play_sound(
    player_uuid_ptr: *const c_char,
    sound_name_ptr: *const c_char,
    sound_category_ptr: *const c_char,
    x: c_double,
    y: c_double,
    z: c_double,
    volume: c_float,
    pitch: c_float,
) {
    let player_uuid_str = get_string(player_uuid_ptr);
    let sound_name = get_string(sound_name_ptr);
    let sound_category = get_string(sound_category_ptr);

    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let player_uuid = uuid::Uuid::parse_str(&player_uuid_str).unwrap();

        ctx.runtime.spawn(async move {
            let player = ctx.plugin_context.server.get_player_by_uuid(player_uuid);

            if let Some(player) = player {
                let sound = match Sound::from_name(&sound_name) {
                    Some(sound) => sound,
                    None => return,
                };

                let category = match SoundCategory::from_name(&sound_category.to_lowercase()) {
                    Some(category) => category,
                    None => return,
                };

                let seed = rng().random::<f64>();

                let position: Vector3<f64> = Vector3::new(x, y, z);

                player
                    .play_sound(sound as u16, category, &position, volume, pitch, seed)
                    .await;
            }
        });
    }
}
