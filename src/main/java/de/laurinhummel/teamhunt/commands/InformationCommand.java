package de.laurinhummel.teamhunt.commands;

import de.laurinhummel.teamhunt.shortcuts.McColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InformationCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.sendMessage("-----------------------------------------------------");
        player.sendMessage(McColors.BLUE + "Defender" + McColors.AQUA + "You have to defend the Ender Dragon");
        player.sendMessage(McColors.RED + "Attacker" + McColors.AQUA + "You have to kill the Ender Dragon");
        player.sendMessage(McColors.WHITE + "If a defender dies, he can spectate the world in spectator mode.");
        player.sendMessage(McColors.WHITE + "If a attacker dies, he can respawn infinitely");
        player.sendMessage(McColors.AQUA + "The game ends when either all defenders are dead or the Ender Dragon has been killed (no matter how)");
        player.sendMessage("-----------------------------------------------------");
        return true;
    }
}
