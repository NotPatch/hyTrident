package com.hysteria.hytrident;

import com.hysteria.hytrident.command.CMDMain;
import com.hysteria.hytrident.configuration.EnchantConfig;
import com.hysteria.hytrident.configuration.LangConfig;
import com.hysteria.hytrident.enchants.EnchantManager;
import com.hysteria.hytrident.listener.TridentThrowListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class HyTrident extends JavaPlugin {

    private static HyTrident instance;

    private EnchantConfig enchantConfig;
    private LangConfig langConfig;
    private EnchantManager enchantManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        this.langConfig = new LangConfig(this);
        this.enchantConfig = new EnchantConfig(this);
        this.enchantManager = new EnchantManager(this);

        new TridentRecipe(this);

        getServer().getPluginManager().registerEvents(new TridentThrowListener(), this);

        getCommand("trident").setExecutor(new CMDMain());

    }

    @Override
    public void onDisable() {
        enchantConfig.save();
        enchantManager.disableEnchantments();
        if(Bukkit.getRecipe(NamespacedKey.fromString("hytrident")) != null){
            Bukkit.removeRecipe(NamespacedKey.fromString("hytrident"));
        }
    }

    public static HyTrident getInstance() {
        return instance;
    }

    public EnchantConfig getEnchantConfig() {
        return enchantConfig;
    }

    public EnchantManager getEnchantManager() {
        return enchantManager;
    }

    public LangConfig getLanguage() {
        return langConfig;
    }
}
