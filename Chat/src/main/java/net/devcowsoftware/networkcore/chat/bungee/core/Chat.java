package net.devcowsoftware.networkcore.chat.bungee.core;

import net.devcowsoftware.networkcore.core.core.bungee.module.Module;
import net.devcowsoftware.networkcore.core.bungee.core.Core;

import java.util.logging.Level;

public final class Chat extends Module
{
    private Core coreInstance;

    private Chat chatInstance;

    @Override
    public void onLoad()
    {
       chatInstance = this;
       coreInstance = Core.getInstance2();
        getLogger().log(Level.INFO, "This is just a Test Loading");
    }


    @Override
    public void onEnable()
    {
        super.onEnable();
        getLogger().log(Level.INFO,"NetworkCore Chat:THis is enabledBUngee");
    }


    @Override
    public void onDisable()
    {
        getLogger().log(Level.INFO,"NetworkCore Chat:THis is enabledBUngee");
    }


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
