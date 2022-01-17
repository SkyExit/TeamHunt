package de.laurinhummel.teamhunt.commands;

import de.laurinhummel.teamhunt.main.Main;
import de.laurinhummel.teamhunt.shortcuts.McColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Objects;

public class MenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration config = Main.getPlugin().getConfig();
            String PREFIX = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
            String MASK = "NGQ4MjU3ZTExNzVhOWFhMTgyMTgxODg5MTI3ZTVhNTQzMGM4MWMzMTViNjNiMjI2ZDAyOTEzZDEyNDk0NSJ9fX0=";
            String FACE = "YmE4YTU1ZmZlNjNhMWZjOTZkYzYyNzA1YzY5Nzc0OWQ2NWY1OTRmMTY2NDM4N2JjODg3NmZlM2FkMzViNzI3MCJ9fX0=";
            Inventory menu = Bukkit.getServer().createInventory(player, 27, McColors.AQUA + "Team" + McColors.GOLD + "Hunt");

            //PLACEHOLDER
            ItemStack placeholder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta placeholdermeta = placeholder.getItemMeta();
            assert placeholdermeta != null;
            placeholdermeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            placeholdermeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            placeholdermeta.setDisplayName(McColors.RED + "PLACEHOLDER");
            placeholder.setItemMeta(placeholdermeta);

            //START TIMER
            ItemStack startTimer = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta startTimerMeta = startTimer.getItemMeta();
            assert startTimerMeta != null;
            startTimerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            startTimerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            startTimerMeta.setDisplayName(McColors.GRAY + "Timer is " + McColors.GREEN + "activated");
            startTimer.setItemMeta(startTimerMeta);

            //STOP TIMER
            ItemStack stopTimer = new ItemStack(Material.RED_CONCRETE);
            ItemMeta stopTimerMeta = stopTimer.getItemMeta();
            assert stopTimerMeta != null;
            stopTimerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            stopTimerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            stopTimerMeta.setDisplayName(McColors.GRAY + "Timer is " + McColors.RED + "deactivated");
            stopTimer.setItemMeta(stopTimerMeta);

            //BE DEFENDER
            ItemStack defender = new ItemStack(Material.BLUE_CONCRETE);
            ItemMeta defenderMeta = defender.getItemMeta();
            assert defenderMeta != null;
            defenderMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            defenderMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            defenderMeta.setDisplayName(McColors.GRAY + "Be a " + McColors.BLUE + "defender");
            ArrayList<String> defenderLore = new ArrayList<>();
            defenderLore.add(McColors.BLUE + "Defenders" + McColors.GRAY + " have to defend the dragon");
            defenderLore.add(McColors.BLUE + "Defenders" + McColors.GRAY + " can respawn infinitely");
            defenderLore.add(McColors.BLUE + "Defenders" + McColors.GRAY + " win, if they killed all attackers");
            defenderMeta.setLore(defenderLore);
            defender.setItemMeta(defenderMeta);

            //BE ATTACKER
            ItemStack attacker = new ItemStack(Material.ORANGE_CONCRETE);
            ItemMeta attackerMeta = attacker.getItemMeta();
            assert attackerMeta != null;
            attackerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            attackerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            attackerMeta.setDisplayName(McColors.GRAY + "Be a " + McColors.GOLD + "attacker");
            ArrayList<String> attackerLore = new ArrayList<>();
            attackerLore.add(McColors.GOLD + "Attackers" + McColors.GRAY + " have to kill the dragon");
            attackerLore.add(McColors.GOLD + "Attackers" + McColors.GRAY + " only have one live each");
            attackerLore.add(McColors.GOLD + "Attackers" + McColors.GRAY + " win, if the Ender Dragon dies");
            attackerMeta.setLore(attackerLore);
            attacker.setItemMeta(attackerMeta);

            //ENABLED HELMET
            ItemStack helmet = Main.createSkull(PREFIX + MASK, "s");
            ItemMeta helmetMeta = helmet.getItemMeta();
            assert helmetMeta != null;
            helmetMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            helmetMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            helmetMeta.setDisplayName(McColors.GRAY + "Helmet is " + McColors.GREEN + "activated");
            helmet.setItemMeta(helmetMeta);

            //DISABLED HELMET (PLAYER HEAD)
            ItemStack head = new ItemStack(Material.LEGACY_SKULL_ITEM);
            SkullMeta headSMeta = (SkullMeta) head.getItemMeta();
            assert headSMeta != null;
            headSMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            headSMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            headSMeta.setDisplayName(McColors.GRAY + "Helmet is " + McColors.RED + "deactivated");
            head.setItemMeta(headSMeta);

            //ENABELED MASK
            ItemStack mask = new ItemStack(Material.GREEN_STAINED_GLASS);
            ItemMeta maskMeta = mask.getItemMeta();
            assert maskMeta != null;
            maskMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            maskMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            maskMeta.setDisplayName(McColors.GRAY + "Mask is " + McColors.GREEN + "activated");
            mask.setItemMeta(maskMeta);

            //DISABLED MASK
            ItemStack face = new ItemStack(Material.RED_STAINED_GLASS);
            ItemMeta faceMeta = face.getItemMeta();
            assert faceMeta != null;
            faceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            faceMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            faceMeta.setDisplayName(McColors.GRAY + "Mask is " + McColors.RED + "deactivated");
            face.setItemMeta(faceMeta);

            //ENABLE HELMET FIRST
            ItemStack enableFirst = new ItemStack(Material.BARRIER);
            ItemMeta enableFirstMeta = enableFirst.getItemMeta();
            assert enableFirstMeta != null;
            enableFirstMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            enableFirstMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            enableFirstMeta.setDisplayName(McColors.GRAY + "Enable helmet first");
            enableFirst.setItemMeta(enableFirstMeta);

            //Be Defender first
            ItemStack beAttackerFirst = new ItemStack(Material.BARRIER);
            ItemMeta beAttackerFirstMeta = beAttackerFirst.getItemMeta();
            assert beAttackerFirstMeta != null;
            beAttackerFirstMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            beAttackerFirstMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            beAttackerFirstMeta.setDisplayName(McColors.GRAY + "Be defender first");
            beAttackerFirst.setItemMeta(beAttackerFirstMeta);

            //SET ITEMS
            menu.setItem(0, placeholder);
            menu.setItem(1, placeholder);
            menu.setItem(2, placeholder);
            menu.setItem(3, placeholder);
            menu.setItem(4, placeholder);
            menu.setItem(5, placeholder);
            menu.setItem(6, placeholder);
            menu.setItem(7, placeholder);
            menu.setItem(8, placeholder);
            menu.setItem(9, placeholder);
            //Timer
            menu.setItem(11, placeholder);
            //DEFENDER
            //ATTACKER
            menu.setItem(14, placeholder);
            //HELMET
            //MASK
            menu.setItem(17, placeholder);
            menu.setItem(18, placeholder);
            menu.setItem(18, placeholder);
            menu.setItem(19, placeholder);
            menu.setItem(20, placeholder);
            menu.setItem(21, placeholder);
            menu.setItem(22, placeholder);
            menu.setItem(23, placeholder);
            menu.setItem(24, placeholder);
            menu.setItem(25, placeholder);

            if(Objects.equals(config.get("Team.defend." + player.getName()), true)) {
                menu.setItem(26, Main.getPlugin().getCompassItem());
            } else {
                menu.setItem(26, beAttackerFirst);
            }

            if(config.get("Timer").equals(true)) {
                menu.setItem(10, startTimer);
            } else {
                menu.setItem(10, stopTimer);
            }

            menu.setItem(12, defender);
            menu.setItem(13, attacker);

            if(Objects.equals(config.get("Player." + player.getName() + ".Head"), true)) {
                menu.setItem(15, helmet);
            } else {
                menu.setItem(15, head);
            }

            if(Objects.equals(config.get("Player." + player.getName() + ".Head"), true)) {
                if(Objects.equals(config.get("Player." + player.getName() + ".Mask"), true)) {
                    menu.setItem(16, mask);
                } else {
                    menu.setItem(16, face);
                }
            } else {
                menu.setItem(16, enableFirst);
            }


            //OPEN INVENTORY
            player.openInventory(menu);
        }
        return false;
    }
}
