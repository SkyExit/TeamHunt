package de.laurinhummel.teamhunt.events;

import de.laurinhummel.teamhunt.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class DontMoveCompass implements Listener {
    @EventHandler
    public void onInventoryClickEven(InventoryClickEvent event) {
        String PREFIX = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
        String MASK = "NGQ4MjU3ZTExNzVhOWFhMTgyMTgxODg5MTI3ZTVhNTQzMGM4MWMzMTViNjNiMjI2ZDAyOTEzZDEyNDk0NSJ9fX0=";
        String FACE = "YmE4YTU1ZmZlNjNhMWZjOTZkYzYyNzA1YzY5Nzc0OWQ2NWY1OTRmMTY2NDM4N2JjODg3NmZlM2FkMzViNzI3MCJ9fX0=";

        ItemStack item1 = Main.createSkull(PREFIX + MASK, "s");
        ItemStack item2 = Main.createSkull(PREFIX + FACE, "s");

        if(Objects.equals(event.getCurrentItem(), Main.getPlugin().getCompassItem())) {
            event.setCancelled(true);
        }

        //Dont move Helmet
        if(event.getCurrentItem() == item1) {
            event.setCancelled(true);
        }

        if(event.getCurrentItem() == item2) {
            event.setCancelled(true);
        }

        if(event.isShiftClick() && (event.getSlot() == 8)) {
            event.setCancelled(true);
        }
    }
}