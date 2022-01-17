package de.laurinhummel.teamhunt.commands;

import de.laurinhummel.teamhunt.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class FullHealthCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Main.getPlugin().getConfig();
        if(Objects.equals(config.get("Timer"), false)) {
            for(Player player : Bukkit.getOnlinePlayers()){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 5));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 5));
            }
        }
        return true;
    }
}