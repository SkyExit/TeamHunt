package de.laurinhummel.teamhunt.main;

import com.mojang.authlib.properties.Property;
import de.laurinhummel.teamhunt.commands.*;
import de.laurinhummel.teamhunt.commands.alt.*;
import de.laurinhummel.teamhunt.events.*;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import de.laurinhummel.teamhunt.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.mojang.authlib.GameProfile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public final class Main extends JavaPlugin {
    private static Main plugin;

    private Timer timer;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        //plugin = this;
        PluginManager pluginManager = Bukkit.getPluginManager();

        timer = new Timer(false, 0);

        getCommand("clearinv").setExecutor((CommandExecutor)new clearInvCommand());
        getCommand("resetTimer").setExecutor((CommandExecutor)new resetTimer());
        getCommand("regenerate").setExecutor((CommandExecutor)new FullHealthCommand());
        getCommand("info").setExecutor((CommandExecutor)new InformationCommand());

        getCommand("menu").setExecutor((CommandExecutor)new MenuCommand());



        pluginManager.registerEvents((Listener)new AttackerGotKilled(), (Plugin) this);
        pluginManager.registerEvents((Listener)new DontDropCompass(), (Plugin) this);
        pluginManager.registerEvents((Listener)new DontInteract(), (Plugin) this);
        pluginManager.registerEvents((Listener)new DontMoveCompass(), (Plugin) this);
        pluginManager.registerEvents((Listener)new DontPlaceBlocks(), (Plugin) this);
        pluginManager.registerEvents((Listener)new DontTakeDamage(), (Plugin) this);
        pluginManager.registerEvents((Listener)new UpdateBar(), (Plugin) this);
        pluginManager.registerEvents((Listener)new dragonGotKilled(), (Plugin) this);
        pluginManager.registerEvents((Listener)new MenuClickListener(), (Plugin) this);


        FileConfiguration config = getPlugin().getConfig();
        getConfig();
        config.addDefault("Team", null);
        config.addDefault("Timer", false);
        config.set("Timer", false);
        config.set("Team", null);
        config.options().copyDefaults(true);
        saveConfig();

    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

    public ItemStack getCompassItem() {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        assert compassMeta != null;
        compassMeta.setDisplayName("§bCompass of the §6PREDATOR");
        ArrayList<String> compassLore = new ArrayList<>();
        compassLore.add("§4I will track the nearest attacker!");
        compassMeta.setLore(compassLore);
        compassMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        compassMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        compass.setItemMeta(compassMeta);
        return compass;
    }

    public static ItemStack createSkull(String url, String name) {
        ItemStack head = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        if(url.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        headMeta.setDisplayName(McColors.GOLD + "Predator" + McColors.AQUA + " Bio Mask");
        headMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        headMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
        headMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        head.setItemMeta(headMeta);
        return head;
    }

    public Player getNearestAttacker(Player DefendPlayer) {
        FileConfiguration config = Main.getPlugin().getConfig();
        Player result = DefendPlayer;
        double lastDistance = Double.MAX_VALUE;
        for(Player p : DefendPlayer.getServer().getOnlinePlayers()) {
            if(DefendPlayer != p) {
                if(DefendPlayer.getWorld().getEnvironment().equals(p.getWorld().getEnvironment())) {
                    double distance = DefendPlayer.getLocation().distance(p.getLocation());
                    if(Objects.equals(config.get("Team.defend." + p.getName()), false)) {
                        if(p.getGameMode().equals(GameMode.SURVIVAL)) {
                            if(distance < lastDistance) {
                                lastDistance = distance;
                                result = p;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
