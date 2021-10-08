package net.devcowsoftware.networkcore.core.bungee.utils.messages;

import net.devcowsoftware.networkcore.core.bungee.core.Core;
import net.md_5.bungee.api.ChatColor;

public class SingularMessages
{
    private final Core coreInstance;

    public SingularMessages(Core coreInstance)
    {
        this.coreInstance = coreInstance;
    }

    public String c(String msg)
    {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public String pluginPrefix = c("&2NetworkCore-Core");




}
