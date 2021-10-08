package net.devcowsoftware.networkcore.core.spigot.managers;

import net.devcowsoftware.networkcore.core.spigot.core.Core;
import net.devcowsoftware.networkcore.core.spigot.utils.messages.SingularMessages;

public class MessageManager
{
    private final Core coreInstance;

    // Messages
    private SingularMessages singularMessages;


    public MessageManager(Core coreInstance)
    {
        this.coreInstance = coreInstance;
    }

    public void messages()
    {
        singularMessages = new SingularMessages(coreInstance.getCoreInstance());

    }

    public SingularMessages getSingularMessages()
    {
        return singularMessages;
    }
}
