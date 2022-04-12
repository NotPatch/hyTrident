package com.hysteria.hytrident.configuration;

import com.hysteria.hytrident.HyTrident;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class AbstractConfig {

    protected HyTrident main;
    private File file;
    protected FileConfiguration fileConfiguration;

    public AbstractConfig(HyTrident main, String fileName){
        this.main = main;
        this.file = new File(main.getDataFolder(), fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try {
            fileConfiguration.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
