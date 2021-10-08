package net.devcowsoftware.networkcore.core.spigot.managers;

import net.devcowsoftware.networkcore.core.spigot.core.Core;
import net.devcowsoftware.networkcore.core.spigot.storage.config.core.ServerConfig;

public class ConfigManager
{
    private final Core coreInstance;

    //Configs
    private ServerConfig serverConfig;

    public ConfigManager(Core coreInstance)
    {
        this.coreInstance = coreInstance;
    }

    public void configs()
    {
        serverConfig = new ServerConfig(coreInstance.getCoreInstance());
        serverConfig.saveDefaultConfig();


    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }
}
