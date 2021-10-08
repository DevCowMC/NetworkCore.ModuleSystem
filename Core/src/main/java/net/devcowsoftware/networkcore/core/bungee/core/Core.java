package net.devcowsoftware.networkcore.core.bungee.core;

import lombok.SneakyThrows;
import net.devcowsoftware.networkcore.core.core.bungee.BungeeModular;
import net.devcowsoftware.networkcore.core.bungee.managers.*;
import net.md_5.bungee.api.plugin.Plugin;

public class Core extends Plugin
{
    private Core coreInstance;
    private static Core coreInstance2;

    //Managers
    private ConfigManager configManager;
    private DatabaseManager databaseManager;
    private MessageManager messageManager;
    private TaskManager taskManager;
    private BungeeModular bungeeModular;

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        //License
        LicenseManager licenseManager = new LicenseManager(getCoreInstance());
        licenseManager.license();
        // Database
        setDatabaseManager(new DatabaseManager(getCoreInstance()));
        getDatabaseManager().db();
        //Commands
        CommandManager commands = new CommandManager(getCoreInstance());
        commands.commands();
        //Events
        EventsManager eventsManager = new EventsManager(getCoreInstance());
        eventsManager.events();
        //
        setTaskManager(new TaskManager(getCoreInstance()));
        getTaskManager().tasks();

        getbungeeModular().onEnable();


    }

    @Override
    public void onLoad()
    {
        // Plugin load logic
        //Instances
        coreInstance = this;
        coreInstance2 = this;
        // Messages
        setMessageManager(new MessageManager(getCoreInstance()));
        getMessageManager().messages();
        //Configs
        setConfigManager(new ConfigManager(getCoreInstance()));
        getConfigManager().configs();

        setBungeeModular(new BungeeModular(getCoreInstance()));
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        getbungeeModular().onDisable();
    }


    public Core getCoreInstance() {
        return coreInstance;
    }

    public static Core getInstance2() {
        return coreInstance2;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public BungeeModular getbungeeModular() {
        return bungeeModular;
    }

    public void setBungeeModular(BungeeModular bungeeModular) {
        this.bungeeModular = bungeeModular;
    }
}
