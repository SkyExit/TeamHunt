package de.laurinhummel.teamhunt.events;

import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import de.laurinhummel.teamhunt.timer.Timer;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class AttackerGotKilled implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        FileConfiguration config = Main.getPlugin().getConfig();
        Timer timer = Main.getPlugin().getTimer();
        Player player = event.getEntity();
        if(Objects.equals(config.get("Timer"), true)) {
            Boolean dream = (Boolean) config.get("Team.attack." + player.getName());
            if(dream != null && dream.equals(true)) {
                //Attacker
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(McColors.GOLD + player.getName() + McColors.AQUA + " was killed");
                }
                player.setGameMode(GameMode.SPECTATOR);
                int def = 0;
                for(Player p1 : Bukkit.getOnlinePlayers()) {
                    if(Objects.equals(config.get("Team.attack." + p1.getName()), true)) {
                        //it is an attacker
                        if(p1.getGameMode().equals(GameMode.SURVIVAL)) {
                            //Is still alive
                            def++;
                        }
                    }
                }

                if(def == 0) {
                    //There are no attackers left
                    config.set("Timer", false);
                    timer.setRunning(false);
                    Location spawn = Objects.requireNonNull(Bukkit.getServer().getWorld("world")).getSpawnLocation();
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(McColors.GOLD + "The defenders have won the game!");
                        p.teleport(spawn);
                        p.setGameMode(GameMode.CREATIVE);
                    }
                }
            } else {
                //Defender
                if(Objects.equals(config.get("Timer"), true)) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(McColors.AQUA + "Wait 30 seconds till respawn");

                    Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                        player.sendMessage(McColors.AQUA + "20 seconds till respawn");
                        Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                            player.sendMessage(McColors.AQUA + "10 seconds till respawn");
                            Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                                player.sendMessage(McColors.AQUA + "5 seconds till respawn");
                                Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                                    player.sendMessage(McColors.AQUA + "4 seconds till respawn");
                                    Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                                        player.sendMessage(McColors.AQUA + "3 seconds till respawn");
                                        Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                                            player.sendMessage(McColors.AQUA + "2 seconds till respawn");
                                            Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                                                player.sendMessage(McColors.AQUA + "1 seconds till respawn");
                                                Bukkit.getScheduler ().runTaskLater (Main.getPlugin(), () -> {
                                                    player.sendMessage(McColors.AQUA + "gogogo");

                                                    ArrayList<Player> allPlayers = new ArrayList<Player>();
                                                    for(Player p : Bukkit.getOnlinePlayers()) {
                                                        if(!p.equals(player) && Objects.equals(config.get("Team.defend." + p.getName()), true) && p.getGameMode().equals(GameMode.SURVIVAL)) {
                                                            allPlayers.add(p);
                                                        }
                                                    }
                                                    if(allPlayers.isEmpty()) {
                                                        player.teleport(Objects.requireNonNull(Bukkit.getServer().getWorld("world")).getSpawnLocation());
                                                    } else {
                                                        int random = new Random().nextInt(allPlayers.size());
                                                        Player picked = allPlayers.get(random);
                                                        player.teleport(picked.getLocation());
                                                    }
                                                    player.setGameMode(GameMode.SURVIVAL);
                                                    event.getEntity().getInventory().setContents(event.getDrops().toArray(new ItemStack[0]));
                                                }, 20); //20 ticks equal 1 second
                                            }, 20); //20 ticks equal 1 second
                                        }, 20); //20 ticks equal 1 second
                                    }, 20); //20 ticks equal 1 second
                                }, 20); //20 ticks equal 1 second
                            }, 5 * 20); //20 ticks equal 1 second
                        }, 10 * 20); //20 ticks equal 1 second
                    }, 10 * 20); //20 ticks equal 1 second
                }
            }
        }
    }
}
