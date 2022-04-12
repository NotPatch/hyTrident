package com.hysteria.hytrident.util;

import com.hysteria.hytrident.HyTrident;

public class ConfigUtil {

    public static boolean isActive(String enchantment){
        return HyTrident.getInstance().getConfig().getBoolean("enchantments." + enchantment);
    }

    public static boolean isEnable(String path){
        return HyTrident.getInstance().getConfig().getBoolean(path);
    }

}
