package net.devcowsoftware.networkcore.core.spigot.storage.config.core;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import net.devcowsoftware.networkcore.core.spigot.core.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class ServerConfig {
    
    private final Core coreInstance;
    private FileConfiguration serverConfig;
    private File serverConfigFile = null;

    public ServerConfig(Core coreInstance) {
        this.coreInstance = coreInstance;
        // saves/initializes the config
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.serverConfigFile == null)
            this.serverConfigFile = new File(this.coreInstance.getDataFolder(), "Core/" + "server.yml");

        this.serverConfig = YamlConfiguration.loadConfiguration(this.serverConfigFile);

        InputStream defaultStream = this.coreInstance.getResource("Core/" + "server.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.serverConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.serverConfig == null)
            reloadConfig();

        return this.serverConfig;
    }

    public void saveConfig() {
        if (this.serverConfig == null || this.serverConfigFile == null)
            return;

        try {
            this.getConfig().save(this.serverConfigFile);
        } catch (IOException e) {
            coreInstance.getLogger().log(Level.SEVERE, "Could not save config to " + this.serverConfigFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.serverConfigFile == null)
            this.serverConfigFile = new File(this.coreInstance.getDataFolder(), "Core/" + "server.yml");

        if (!this.serverConfigFile.exists()) {
            this.coreInstance.saveResource("Core/" + "server.yml", false);
        }
    }

}

