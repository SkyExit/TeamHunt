package de.laurinhummel.teamhunt.commands.alt;

import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

//Attackers have to Attack the defenders

public class beAttackerCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        FileConfiguration config = Main.getPlugin().getConfig();
        config.set("Team.attack." + player.getName(), true);
        config.set("Team.defend." + player.getName(), false);
        Main.getPlugin().saveConfig();
        player.sendMessage(McColors.AQUA + "You are a Attacker now!");
        String altname = player.getName();
        player.setDisplayName(McColors.RED + altname + McColors.WHITE);
        player.setPlayerListName(McColors.RED + "[ATTACKER] " + altname);
        return false;
    }
}
