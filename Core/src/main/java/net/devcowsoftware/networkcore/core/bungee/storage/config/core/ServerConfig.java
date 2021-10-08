package net.devcowsoftware.networkcore.core.bungee.storage.config.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ServerConfig {

    private Plugin plugin;
    private Configuration config;
    private String fileName = null;

    public ServerConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        loadConfig();
    }

    public Configuration getConfig() {
        if (this.config == null)
            loadConfig();
        return config;
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(plugin.getDataFolder(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(loadResource(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File loadResource(String resource) {
        File folder = plugin.getDataFolder() ;
        if (!folder.exists())
            folder.mkdir();
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = plugin.getResourceAsStream(resource);
                     OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }
}

