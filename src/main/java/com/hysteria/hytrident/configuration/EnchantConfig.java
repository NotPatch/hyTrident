package com.hysteria.hytrident.configuration;

import com.hysteria.hytrident.HyTrident;

public class EnchantConfig extends AbstractConfig{

    public EnchantConfig(HyTrident main) {
        super(main, "enchants.yml");
        if(!fileConfiguration.isSet("inferno")){
            fileConfiguration.set("inferno.name", "Inferno");
            fileConfiguration.set("inferno.maxLevel", 3);
            fileConfiguration.set("inferno.startLevel", 1);
            fileConfiguration.set("inferno.isTreasure", true);
            fileConfiguration.set("inferno.isCursed", false);
        }
        if(!fileConfiguration.isSet("glow")){
            fileConfiguration.set("glow.name", "Glow");
            fileConfiguration.set("glow.maxLevel", 3);
            fileConfiguration.set("glow.startLevel", 1);
            fileConfiguration.set("glow.isTreasure", true);
            fileConfiguration.set("glow.isCursed", false);
        }
        save();
    }

    public String getName(String enchantment){
        return fileConfiguration.getString(enchantment + ".name");
    }

    public int getMaxLevel(String enchantment){
        return fileConfiguration.getInt(enchantment + ".maxLevel");
    }

    public int getStartLevel(String enchantment){
        return fileConfiguration.getInt(enchantment + ".startLevel");
    }

    public boolean isTreasure(String enchantment){
        return fileConfiguration.getBoolean(enchantment + ".isTreasure");
    }

    public boolean isCursed(String enchantment){
        return fileConfiguration.getBoolean(enchantment + ".isCursed");
    }

}
