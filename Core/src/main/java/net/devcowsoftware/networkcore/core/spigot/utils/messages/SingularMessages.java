package net.devcowsoftware.networkcore.core.spigot.utils.messages;

import net.devcowsoftware.networkcore.core.spigot.core.Core;
import org.bukkit.ChatColor;

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
