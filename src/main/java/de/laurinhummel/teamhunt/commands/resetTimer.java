package de.laurinhummel.teamhunt.commands;

import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.timer.Timer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class resetTimer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Timer timer = Main.getPlugin().getTimer();
        timer.setRunning(false);
        timer.resetTimer();
        sender.sendMessage(ChatColor.GRAY + "Der Timer wurde zurückgesetzt.");
        return true;
    }
}
