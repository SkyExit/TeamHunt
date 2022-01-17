package de.laurinhummel.teamhunt.commands.alt;

import de.laurinhummel.teamhunt.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class getCompassCommand<task> implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Main.getPlugin().getConfig();
        String PREFIX = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
        String MASK = "NGQ4MjU3ZTExNzVhOWFhMTgyMTgxODg5MTI3ZTVhNTQzMGM4MWMzMTViNjNiMjI2ZDAyOTEzZDEyNDk0NSJ9fX0=";
        String FACE = "YmE4YTU1ZmZlNjNhMWZjOTZkYzYyNzA1YzY5Nzc0OWQ2NWY1OTRmMTY2NDM4N2JjODg3NmZlM2FkMzViNzI3MCJ9fX0=";
        ItemStack item;
        Player player = (Player) sender;
        if (sender != null) {
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


        return true;
    }
}
