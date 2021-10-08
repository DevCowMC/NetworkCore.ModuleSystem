package net.devcowsoftware.networkcore.core.bungee.managers;

import net.devcowsoftware.networkcore.core.bungee.core.Core;
import net.devcowsoftware.networkcore.core.bungee.utils.messages.SingularMessages;

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
