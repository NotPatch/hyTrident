package com.hysteria.hytrident.enchants;

import com.hysteria.hytrident.HyTrident;
import com.hysteria.hytrident.util.ConfigUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class EnchantManager {

    public Inferno inferno;
    public Increase increase;
    public Glow glow;
    public Stab stab;

    public static ArrayList<Enchantment> register = new ArrayList<>();
    public static ArrayList<Enchantment> registeredEnchants = new ArrayList<>();

    public EnchantManager(HyTrident main){
        if(ConfigUtil.isActive("inferno")){
            inferno = new Inferno("inferno");
            main.getServer().getPluginManager().registerEvents(inferno, main);
            register.add(inferno);
        }
        if(ConfigUtil.isActive("glow")){
            glow = new Glow("glow");
            main.getServer().getPluginManager().registerEvents(glow, main);
            register.add(glow);
        }
        if(ConfigUtil.isActive("stab")){
            stab = new Stab("stab");
            main.getServer().getPluginManager().registerEvents(stab, main);
            register.add(stab);
        }
        if(ConfigUtil.isActive("increase")){
            increase = new Increase("increase");
            main.getServer().getPluginManager().registerEvents(increase, main);
            register.add(increase);
        }

        register.forEach(this::registerEnchantment);
    }

    public void disableEnchantments(){
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");
            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
            for (Enchantment enchantment : registeredEnchants){
                if(byKey.containsKey(enchantment.getKey())) {
                    byKey.remove(enchantment.getKey());
                }
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");
            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);
            for (Enchantment enchantment : registeredEnchants){
                if(byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }
        } catch (Exception ignored) { }

    }

    private void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
            HyTrident.getInstance().getLogger().info("Enchantment '" + enchantment.getName() + "' loaded");
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            registeredEnchants.add(enchantment);
        }
    }


}
