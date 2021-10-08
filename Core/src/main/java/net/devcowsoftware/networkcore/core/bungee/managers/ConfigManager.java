package net.devcowsoftware.networkcore.core.bungee.managers;

import net.devcowsoftware.networkcore.core.bungee.core.Core;
import net.devcowsoftware.networkcore.core.bungee.storage.config.core.ServerConfig;

import java.io.IOException;

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

        serverConfig = new ServerConfig(coreInstance.getCoreInstance(),  "server.yml");
        serverConfig.loadResource("server.yml");

    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }
}
