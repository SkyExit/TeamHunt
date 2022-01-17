package de.laurinhummel.teamhunt.timer;

import de.laurinhummel.teamhunt.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {

    private boolean running;
    private int seconds;
    private int minutes;
    private int hours;
    private int days;

    public Timer(boolean running, int time) {
        this.running = running;
        this.seconds = time;

        run();
    }

    public boolean isRunning() {
        return running;
    }

    public int getTime() {
        return seconds;
    }

    public void addTime() {
        this.seconds++;
        if(this.seconds == 60) {
            this.seconds = 0;
            this.minutes++;
            if(this.minutes == 60) {
                this.minutes = 0;
                this.hours++;
                if(this.hours == 24) {
                    this.hours = 0;
                    this.days++;
                }
            }
        }
    }

    public void resetTimer() {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
        this.days = 0;
    }

    public void setRunning(boolean bool) {
        this.running = bool;
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(!isRunning()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED.toString() + ChatColor.BOLD + "Timer ist pausiert"));
                continue;
            }

            if(this.minutes == 0) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + this.seconds + "s"));
            } else if(this.hours == 0) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + this.minutes + "m " + this.seconds + "s"));
            }
            else if(this.days == 0) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + this.hours + "h " + this.minutes + "m " + this.seconds + "s"));
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + this.days + "d " + this.hours + "h " + this.minutes + "m " + this.seconds + "s"));
            }


        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                sendActionBar();

                if(!isRunning()) {
                    return;
                }

                addTime();

            }
        }.runTaskTimer(Main.getPlugin(), 20, 20); //20    20
    }

}
