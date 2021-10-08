package net.devcowsoftware.networkcore.core.core.bungee;

import net.devcowsoftware.networkcore.core.core.bungee.module.Module;
import net.devcowsoftware.networkcore.core.core.bungee.module.ModuleLoader;
import net.devcowsoftware.networkcore.core.bungee.core.Core;

import java.lang.reflect.Field;

public final class BungeeModular {

    private final Core coreInstance;
    private ModuleLoader moduleLoader;
    private String version;

    public BungeeModular(Core coreInstance)
    {
        this.coreInstance = coreInstance;
    }

    public void onEnable() {
        // Plugin startup logic
        version = coreInstance.getDescription().getVersion();
        coreInstance.getLogger().info("Starting module loader...");
        moduleLoader = new ModuleLoader(coreInstance.getDataFolder().getAbsolutePath() + "/modules/");

    }

    public void onDisable() {
        for ( Module module : moduleLoader.getLoadedModules() ) {
            module.onDisable();
        }
    }


    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }

    public String getVersion() {
        return version;
    }
}
