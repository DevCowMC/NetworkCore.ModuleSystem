package net.devcowsoftware.networkcore.core.core.bungee.module;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.devcowsoftware.networkcore.core.bungee.core.Core;

import java.io.File;
import java.util.logging.Logger;

@Getter
@Setter(AccessLevel.PACKAGE)
public abstract class Module {

    private Core coreinstance2;
    private static ModuleLoader moduleLoader = ModuleLoader.getCoreInstance();
    private String name;
    private String author;
    private double version;
    private String[] dependencies;
    private File file;
    private String main;
    private File dataFolder, configFile;

    private Logger logger;

    public Module() {
        coreinstance2 = Core.getInstance2();
        this.logger = Logger.getLogger(getName());
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
