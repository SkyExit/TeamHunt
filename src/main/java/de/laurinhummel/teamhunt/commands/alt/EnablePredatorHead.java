package de.laurinhummel.teamhunt.commands.alt;

import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EnablePredatorHead implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Main.getPlugin().getConfig();
        Player player = (Player) sender;
        if(args.length == 1) {
            if(args[0].equals("enable")) {
                config.set("Player." + player.getName() + ".Head", true);
                player.sendMessage(McColors.AQUA + "Head Activated");
            } else if(args[0].equals("disable")) {
                config.set("Player." + player.getName() + ".Head", false);
                player.sendMessage(McColors.AQUA + "Head Deactivated");
            } else {
                player.sendMessage(McColors.RED + "Please type 'enable' or 'disable'");
            }
            Main.getPlugin().saveConfig();
        } else {
            player.sendMessage(McColors.RED + "Please type 'enable' or 'disable'");
        }

        return false;
    }
}