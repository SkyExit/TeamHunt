package de.laurinhummel.teamhunt.commands.alt;

import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TimerCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Main.getPlugin().getConfig();
        Player player = (Player) sender;
        if(args.length == 1) {
            if(args[0].equals("start")) {
                if(config.get("Team") != null) {
                    config.set("Timer", true);
                    player.sendMessage(McColors.AQUA + "Timer Activated");
                } else {
                    player.sendMessage(McColors.RED + "There are no Teams set on this Server! Use /bedefender and /beattacker");
                }
            } else if(args[0].equals("stop")) {
                config.set("Timer", false);
                player.sendMessage(McColors.AQUA + "Timer Deactivated");
            } else {
                player.sendMessage(McColors.RED + "Please type 'start' or 'stop'");
            }
            Main.getPlugin().saveConfig();
        } else {
            player.sendMessage(McColors.RED + "Please type 'start' or 'stop'");
        }

        return false;
    }
}