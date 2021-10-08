package net.devcowsoftware.networkcore.core.core.spigot;


import net.devcowsoftware.networkcore.core.core.spigot.module.Module;
import net.devcowsoftware.networkcore.core.core.spigot.module.ModuleLoader;
import net.devcowsoftware.networkcore.core.spigot.core.Core;



public final class SpigotModular {

    private final Core coreInstance;
    private ModuleLoader moduleLoader;
    private String version;

    public SpigotModular(Core coreInstance)
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
