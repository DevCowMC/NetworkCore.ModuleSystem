package net.devcowsoftware.networkcore.core.core.spigot.module;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.devcowsoftware.networkcore.core.spigot.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Logger;

@Getter
@Setter(AccessLevel.PACKAGE)
public abstract class Module {

    private Core coreInstance;
    private static ModuleLoader moduleLoader = ModuleLoader.getCoreInstance();
    private String name;
    private String author;
    private double version;
    private String[] dependencies;
    private File file;
    private String main;
    private CommandMap commandMap;
    private File dataFolder, configFile;
    private FileConfiguration config;
    private Logger logger;

    public Module() {
        coreInstance = Core.getInstance2();
        final Field bukkitCommandMap;
        this.logger = Logger.getLogger(getName());
    }

    /**
     * setup the config for this module
     */
    void setupConfig() {
        dataFolder = new File(file, name);
        dataFolder.mkdirs();

        System.out.println(dataFolder);

        configFile = new File(dataFolder, "config.yml");
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * this method saves the config
     */
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * will be called when this module gets enabled
     */
    public void onEnable() {

    }

    /**
     * will be called when the module gets disabled
     */
    public void onDisable() {

    }

    /**
     * will be called when the module first gets loaded
     */
    public void onLoad() {
    }

    protected static <M> M getModule(String s) {
        for (Module module : moduleLoader.getLoadedModules()) {
            if (module.getName().equalsIgnoreCase(s)) {
                return (M) module;
            }
        }
        return null;
    }

}
