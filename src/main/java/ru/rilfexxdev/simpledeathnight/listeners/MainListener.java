package ru.rilfexxdev.simpledeathnight.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.rilfexxdev.simpledeathnight.BossBar;

public class MainListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (BossBar.getBossBar() != null) {
            BossBar.addPlayer(p);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (BossBar.getBossBar() != null) {
            BossBar.removePlayer(p);
        }
    }
}
