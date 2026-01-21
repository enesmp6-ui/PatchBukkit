package org.patchbukkit.loader;

import io.papermc.paper.plugin.provider.util.ProviderUtil;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader {

    public JavaPlugin createPlugin(String mainClass) {
        try {
            return ProviderUtil.loadClass(
                mainClass,
                JavaPlugin.class,
                this.getClass().getClassLoader()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
