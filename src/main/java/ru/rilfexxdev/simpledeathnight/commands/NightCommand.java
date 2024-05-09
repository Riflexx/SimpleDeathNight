package ru.rilfexxdev.simpledeathnight.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.rilfexxdev.simpledeathnight.BossBar;
import ru.rilfexxdev.simpledeathnight.Main;

import static ru.rilfexxdev.simpledeathnight.utils.Hex.color;

public class NightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings[0].equalsIgnoreCase("info")) {
                player.sendMessage(color(Main.getPlugin().getConfig().getString("messages.info")).replaceAll("%nl%", "\n"));
                return true;
            } else if (strings[0].equalsIgnoreCase("start")) {
                if (commandSender.hasPermission("simpledeathnight.start")) {

                    BossBar.startScheduler();
                }
            }
        }
        return true;
    }
}
