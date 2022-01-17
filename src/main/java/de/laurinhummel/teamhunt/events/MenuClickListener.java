package de.laurinhummel.teamhunt.events;

import de.laurinhummel.teamhunt.commands.MenuCommand;
import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import de.laurinhummel.teamhunt.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class MenuClickListener implements Listener {
    @EventHandler
    private void inventoryClick(InventoryClickEvent event) {
        Timer timer = Main.getPlugin().getTimer();
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals(McColors.AQUA + "Team" + McColors.GOLD + "Hunt")) {
            if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR)) || (event.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE))) {
                return;
            }
            event.setCancelled(true);
            FileConfiguration config = Main.getPlugin().getConfig();
            //TIMER
            if(event.getSlot() == 10) {
                //TIMER ACTIVATED
                if(Objects.equals(Objects.requireNonNull(event.getCurrentItem()).getType(), Material.GREEN_CONCRETE)) {
                    player.sendMessage("Timer deactivated");
                    config.set("Timer", false);
                    timer.setRunning(false);
                } else {
                    if(config.get("Team") != null) {
                        config.set("Timer", true);
                        timer.setRunning(true);
                        player.sendMessage(McColors.AQUA + "Timer Activated");
                    } else {
                        player.sendMessage(McColors.RED + "There are no Teams set on this Server! Use /menu and select a color to join a team");
                    }
                }

            } else if(event.getSlot() == 12) {
                //DEFENDER
                config.set("Team.defend." + player.getName(), true);
                config.set("Team.attack." + player.getName(), false);
                Main.getPlugin().saveConfig();
                player.sendMessage(McColors.AQUA + "You are a Defender now!");
                String altname = player.getName();
                player.setDisplayName(McColors.BLUE + altname + McColors.WHITE);
                player.setPlayerListName(McColors.BLUE + "[DEFENDER] " + altname);

            } else if(event.getSlot() == 13) {
                //ATTACKER
                config.set("Team.attack." + player.getName(), true);
                config.set("Team.defend." + player.getName(), false);
                Main.getPlugin().saveConfig();
                player.sendMessage(McColors.AQUA + "You are a Attacker now!");
                String altname = player.getName();
                player.setDisplayName(McColors.RED + altname + McColors.WHITE);
                player.setPlayerListName(McColors.RED + "[ATTACKER] " + altname);

            } else if(event.getSlot() == 15) {
                //HELMET
                if(Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.SKELETON_SKULL)) {
                    config.set("Player." + player.getName() + ".Head", true);
                    player.sendMessage(McColors.AQUA + "Head Activated");
                } else {
                    config.set("Player." + player.getName() + ".Head", false);
                    player.sendMessage(McColors.AQUA + "Head Deactivated");
                }
            } else if(event.getSlot() == 16) {
                //MASK
                if(Objects.requireNonNull(event.getCurrentItem()).getType() != Material.BARRIER) {
                    String PREFIX = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
                    String MASK = "NGQ4MjU3ZTExNzVhOWFhMTgyMTgxODg5MTI3ZTVhNTQzMGM4MWMzMTViNjNiMjI2ZDAyOTEzZDEyNDk0NSJ9fX0=";
                    if(event.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS)) {
                        config.set("Player." + player.getName() + ".Mask", false);
                        player.sendMessage(McColors.AQUA + "Mask Deactivated");
                    } else {
                        config.set("Player." + player.getName() + ".Mask", true);
                        player.sendMessage(McColors.AQUA + "Mask Activated");
                    }
                }

            } else if(event.getSlot() == 26) {
                //GET COMPASS
                if(Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.COMPASS)) {
                    String PREFIX = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
                    String MASK = "NGQ4MjU3ZTExNzVhOWFhMTgyMTgxODg5MTI3ZTVhNTQzMGM4MWMzMTViNjNiMjI2ZDAyOTEzZDEyNDk0NSJ9fX0=";
                    String FACE = "YmE4YTU1ZmZlNjNhMWZjOTZkYzYyNzA1YzY5Nzc0OWQ2NWY1OTRmMTY2NDM4N2JjODg3NmZlM2FkMzViNzI3MCJ9fX0=";
                    ItemStack item;
                    player.getInventory().setItem(8, Main.getPlugin().getCompassItem());
                    if(Objects.equals(config.get("Player." + player.getName() + ".Head"), true)) {
                        if(Objects.equals(config.get("Player." + player.getName() + ".Mask"), true)) {
                            item = Main.createSkull(PREFIX + MASK, "s");
                            player.getInventory().setHelmet(item);
                        } else {
                            item = Main.createSkull(PREFIX + FACE, "s");
                            player.getInventory().setHelmet(item);
                        }

                    }
                }
            }
            //player.closeInventory();
            player.chat("/menu");
        }
    }
}
