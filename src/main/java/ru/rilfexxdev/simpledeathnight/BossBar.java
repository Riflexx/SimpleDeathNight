package ru.rilfexxdev.simpledeathnight;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.rilfexxdev.simpledeathnight.utils.TimeFormatter;

import static ru.rilfexxdev.simpledeathnight.utils.Hex.color;

public class BossBar {
    private static org.bukkit.boss.BossBar bossBar;
    public static int timePreparation = 0;
    public static int timeNight = 0;
    public static void createBossBar() {
        bossBar = Bukkit.createBossBar("", BarColor.valueOf(Main.getPlugin().getConfig().getString("settings.barcolor")), BarStyle.valueOf(Main.getPlugin().getConfig().getString("settings.barstyle")));
    }

    public static void startScheduler() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }
        timePreparation = Main.getPlugin().getConfig().getInt("timers.preparation");
        Bukkit.broadcastMessage(color(Main.getPlugin().getConfig().getString("messages.started")).replaceAll("%time%", TimeFormatter.format(timePreparation)).replaceAll("%nl%", "\n"));
        new BukkitRunnable() {
            @Override
            public void run() {
                if (timePreparation != 0) {
                    timePreparation--;
                    bossBar.setTitle(color(Main.getPlugin().getConfig().getString("bossbars.preparation")).replaceAll("%time%", TimeFormatter.format(timePreparation)));
                    bossBar.setProgress((double) timePreparation / Main.getPlugin().getConfig().getInt("timers.preparation"));
                } else {
                    startScheduler2();
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }
    public static void startScheduler2() {
        timeNight = Main.getPlugin().getConfig().getInt("timers.night");
        Bukkit.broadcastMessage(color(Main.getPlugin().getConfig().getString("messages.preparation-stoped")).replaceAll("%nl%", "\n"));
        new BukkitRunnable() {
            @Override
            public void run() {
                if (timeNight != 0) {
                    timeNight--;
                    bossBar.setTitle(color(Main.getPlugin().getConfig().getString("bossbars.night")).replaceAll("%time%", TimeFormatter.format(timeNight)));
                    bossBar.setProgress((double) timeNight / Main.getPlugin().getConfig().getInt("timers.night"));
                } else {
                    removeBossBar();
                    Bukkit.broadcastMessage(color(Main.getPlugin().getConfig().getString("messages.night-stoped")).replaceAll("%nl%", "\n"));
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

    public static void removeBossBar() {bossBar.removeAll(); bossBar = null;}
    public static void addPlayer(Player player) {bossBar.addPlayer(player);}
    public static void removePlayer(Player player) {bossBar.removePlayer(player);}
    public static org.bukkit.boss.BossBar getBossBar() {return bossBar;}
}
