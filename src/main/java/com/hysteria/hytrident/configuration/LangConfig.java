package com.hysteria.hytrident.configuration;

import com.hysteria.hytrident.HyTrident;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Trident;

public class LangConfig {

    FileConfiguration fileConfiguration;

    public LangConfig(HyTrident main) {
        fileConfiguration = main.getConfig();
        if(!fileConfiguration.isSet("lang.track-notify")){
            fileConfiguration.set("lang.track-notify", "&aYour trident is in the %x%, %y%, %z%");
        }
        if(!fileConfiguration.isSet("lang.track-nosignal")){
            fileConfiguration.set("lang.track-nosignal", "&aTrident can't longer be traced&7. &aLast location &e%x%&7, &e%y%&7, &e%z%&7.");
        }
        if(!fileConfiguration.isSet("lang.reload")){
            fileConfiguration.set("lang.reload", "&aConfig reloaded!");
        }
        main.saveConfig();
    }

    public String getLastTridentLocation(Trident trident){
        String message = fileConfiguration.getString("lang.track-nosignal");
        message = message.replaceAll("%x%", String.valueOf((int) trident.getLocation().getX()));
        message = message.replaceAll("%y%", String.valueOf((int) trident.getLocation().getY()));
        message = message.replaceAll("%z%", String.valueOf((int) trident.getLocation().getZ()));
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    public String getTridentLocation(Trident trident){
        String message = fileConfiguration.getString("lang.track-notify");
        message = message.replaceAll("%x%", String.valueOf((int) trident.getLocation().getX()));
        message = message.replaceAll("%y%", String.valueOf((int) trident.getLocation().getY()));
        message = message.replaceAll("%z%", String.valueOf((int) trident.getLocation().getZ()));
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }
}
