package ru.rilfexxdev.simpledeathnight;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rilfexxdev.simpledeathnight.commands.NightCommand;
import ru.rilfexxdev.simpledeathnight.listeners.MainListener;

public final class Main extends JavaPlugin {
    private static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        BossBar.createBossBar();
        Bukkit.getPluginManager().registerEvents(new MainListener(), this);
        getCommand("night").setExecutor(new NightCommand());
    }

    @Override
    public void onDisable() {

    }
    public static Main getPlugin() {return plugin;}
}
