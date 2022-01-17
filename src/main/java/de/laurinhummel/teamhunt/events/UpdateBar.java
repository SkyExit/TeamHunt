package de.laurinhummel.teamhunt.events;

import de.laurinhummel.teamhunt.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class UpdateBar implements Listener {



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        BossBar bar;
        FileConfiguration config = Main.getPlugin().getConfig();
        bar = Bukkit.createBossBar("Initiate..." , BarColor.GREEN, BarStyle.SOLID);
        bar.addPlayer(e.getPlayer());
        bar.setTitle("Initiate...");
        bar.setProgress(1.0);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            double st = 1.0;
            @Override
            public void run() {
                FileConfiguration config = Main.getPlugin().getConfig();
                if(Objects.equals(config.get("Timer"), true)) {
                    st = st - 0.025;
                    bar.setProgress(st);
                    if(st <= 0.025) {
                        st = 1.025;

                        Player player = e.getPlayer();
                        Player target = Main.getPlugin().getNearestAttacker(player);

                        if(target == null) {
                            bar.setTitle("Target: " + "unknown" + "   " + "Dimension: " + "other dimension");
                        }

                        assert target != null;
                        bar.setTitle("Target: " + target.getName() + "   " + "Dimension: " + target.getWorld().getEnvironment());

                        player.setCompassTarget(target.getLocation());


                    }
                }
            }
        }, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }
}
