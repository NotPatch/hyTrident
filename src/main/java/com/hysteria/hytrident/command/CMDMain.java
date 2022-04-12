package com.hysteria.hytrident.command;

import com.hysteria.hytrident.HyTrident;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CMDMain implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            ItemStack trident = new ItemStack(Material.TRIDENT);
            trident.addEnchantment(HyTrident.getInstance().getEnchantManager().inferno, 3);
            trident.addEnchantment(HyTrident.getInstance().getEnchantManager().glow, 3);
            trident.addEnchantment(HyTrident.getInstance().getEnchantManager().increase, 3);
            trident.addEnchantment(HyTrident.getInstance().getEnchantManager().stab, 2);
            p.getInventory().addItem(trident);


        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
