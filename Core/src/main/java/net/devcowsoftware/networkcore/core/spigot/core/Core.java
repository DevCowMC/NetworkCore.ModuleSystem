package net.devcowsoftware.networkcore.core.spigot.core;

import net.devcowsoftware.networkcore.core.core.spigot.SpigotModular;
import net.devcowsoftware.networkcore.core.spigot.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin
{
    private Core coreInstance;
    private static Core coreInstance2;

    //Managers
    private ConfigManager configManager;
    private DatabaseManager databaseManager;
    private MessageManager messageManager;
    private TaskManager taskManager;
    private SpigotModular spigotModular;

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

        getSpigotModular().onEnable();


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

        setSpigotModular(new SpigotModular(getCoreInstance()));
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        getSpigotModular().onDisable();
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

    public SpigotModular getSpigotModular() {
        return spigotModular;
    }

    public void setSpigotModular(SpigotModular spigotModular) {
        this.spigotModular = spigotModular;
    }
}
