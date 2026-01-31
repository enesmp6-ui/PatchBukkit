package org.patchbukkit.bridge;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.util.UUID;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class NativePatchBukkit {
    private static final Linker LINKER = Linker.nativeLinker();

    private static MethodHandle sendMessageNative;
    private static MethodHandle registerEventNative;

    // Called from Rust during initialization
    public static void initCallbacks(long sendMessageAddr, long registerEventAddr) {
        // void rust_send_message(const char* uuid, const char* message)
        sendMessageNative = LINKER.downcallHandle(
            MemorySegment.ofAddress(sendMessageAddr),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
        );

        // void rust_register_event(void* listener, void* plugin)
        registerEventNative = LINKER.downcallHandle(
            MemorySegment.ofAddress(registerEventAddr),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
        );
    }

    public static void sendMessage(UUID uuid, String message) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment uuidStr = arena.allocateFrom(uuid.toString());
            MemorySegment msgStr = arena.allocateFrom(message);

            sendMessageNative.invokeExact(uuidStr, msgStr);
        } catch (Throwable t) {
            throw new RuntimeException("Failed to call native sendMessage", t);
        }
    }

    public static void registerEvent(Listener listener, Plugin plugin) {
        // For objects, you have options:
        // 1. Pass identifying info (strings, IDs) instead of object refs
        // 2. Store objects in a Java-side registry and pass an ID
        // 3. Use JNI NewGlobalRef from Rust side if you need to hold refs

        // Simple approach - pass plugin name, handle lookup in Rust
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment pluginName = arena.allocateFrom(plugin.getName());
            // ...
        } catch (Throwable t) {
            throw new RuntimeException("Failed to register event", t);
        }
    }
}
