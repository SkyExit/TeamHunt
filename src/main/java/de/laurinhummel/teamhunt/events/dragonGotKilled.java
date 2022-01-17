package de.laurinhummel.teamhunt.events;

import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class dragonGotKilled implements Listener {
    @EventHandler
    public void onDragonKill(EntityDeathEvent e) {
        Entity deadEntity = e.getEntity();
        if(deadEntity instanceof EnderDragon) {
            FileConfiguration config = Main.getPlugin().getConfig();
            config.set("Timer", false);
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(McColors.GOLD + "THE DRAGON IS DEAD!");
                p.setGameMode(GameMode.SPECTATOR);
                Objects.requireNonNull(p.getLocation().getWorld()).spawnEntity(p.getLocation(), EntityType.FIREWORK);
            }
        }

    }
}
