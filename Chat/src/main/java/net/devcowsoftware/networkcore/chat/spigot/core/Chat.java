package net.devcowsoftware.networkcore.chat.spigot.core;

import net.devcowsoftware.networkcore.core.core.spigot.module.Module;
import net.devcowsoftware.networkcore.core.spigot.core.Core;

public final class Chat extends Module
{
    private Core coreInstance;

    private Chat chatInstance;

    @Override
    public void onLoad()
    {
       chatInstance = this;
       coreInstance = this.getCoreInstance();
       getLogger().info("NetworkCore Chat:THis is loading");
    }


    @Override
    public void onEnable()
    {
        super.onEnable();
        getLogger().info("NetworkCore Chat:THis is enabled");
    }


    @Override
    public void onDisable()
    {
        getLogger().info("NetworkCore Chat:THis is disabled");
    }

    @Override
    public Core getCoreInstance() {
        return coreInstance;
    }


    @Override
    public String getName() {
        return "ChatModule";
    }

    public Chat getChatInstance() {
        return chatInstance;
    }
}
