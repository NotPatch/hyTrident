package com.hysteria.hytrident.enchants;

import com.hysteria.hytrident.HyTrident;
import com.hysteria.hytrident.configuration.EnchantConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class Increase extends Enchantment implements Listener {

    EnchantConfig config = HyTrident.getInstance().getEnchantConfig();
    String name;

    public Increase(String namespace) {
        super(new NamespacedKey(HyTrident.getInstance(), namespace));
        this.name = namespace;
    }

    @Override
    public String getName() {
        return config.getName(name);
    }

    @Override
    public int getMaxLevel() {
        return config.getMaxLevel(name);
    }

    @Override
    public int getStartLevel() {
        return config.getStartLevel(name);
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TRIDENT;
    }

    @Override
    public boolean isTreasure() {
        return config.isTreasure(name);
    }

    @Override
    public boolean isCursed() {
        return config.isCursed(name);
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e){

        if(!(e.getEntity() instanceof Trident)) return;
        if(!(e.getEntity().getShooter() instanceof Player)) return;
        if(e.getHitBlock() != null) return;

        Trident trident = (Trident) e.getEntity();
        ItemStack item = trident.getItem();

        if(item.getItemMeta().getEnchants().isEmpty()) return;
        if(!item.getItemMeta().hasEnchant(this)) return;
        int level = item.getItemMeta().getEnchantLevel(this);

        if(e.getHitEntity() == null) return;

        Entity target = e.getHitEntity();

        for(int i = 0; i <= level; i++){
            Trident strike = target.getWorld().spawn(target.getLocation().clone().add(0, 5, 0), Trident.class);
            strike.setCritical(true);
        }

    }

}
