package org.patchbukkit.events;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.gson.JsonObject;

public class PatchBukkitEventSerializer {
    public static String serialize(Event event) {
        JsonObject json = new JsonObject();

        if (event instanceof PlayerJoinEvent joinEvent) {
            var joinMessage = joinEvent.joinMessage();
            if (joinMessage != null) {
                json.addProperty("playerUuid", joinEvent.getPlayer().getUniqueId().toString());
                json.addProperty("joinMessage", joinMessage.toString());
            }
        }

        return json.toString();
    }
}
