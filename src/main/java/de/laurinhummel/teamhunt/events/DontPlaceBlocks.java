package de.laurinhummel.teamhunt.events;

import de.laurinhummel.teamhunt.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

public class DontPlaceBlocks implements Listener {
    @EventHandler
    public void onBlockBreak(BlockPlaceEvent event) {
        FileConfiguration config = Main.getPlugin().getConfig();
        if(Objects.equals(config.get("Timer"), false)) {
            event.setCancelled(true);
        }
    }
}
