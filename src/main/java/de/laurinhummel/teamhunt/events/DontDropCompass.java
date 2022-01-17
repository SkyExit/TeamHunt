package de.laurinhummel.teamhunt.events;

import de.laurinhummel.teamhunt.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DontDropCompass implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if(event.getItemDrop().getItemStack().equals(Main.getPlugin().getCompassItem())) {
            event.setCancelled(true);
        }
    }
}
