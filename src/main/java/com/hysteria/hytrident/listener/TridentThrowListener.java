package com.hysteria.hytrident.listener;

import com.hysteria.hytrident.HyTrident;
import com.hysteria.hytrident.task.TridentTrackTask;
import com.hysteria.hytrident.util.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class TridentThrowListener implements Listener {

    @EventHandler
    public void onThrow(ProjectileLaunchEvent e){
        if(ConfigUtil.isEnable("trident-track")){
            if (e.getEntity() instanceof Trident) {
                if (e.getEntity().getShooter() instanceof Player) {
                    Trident trident = (Trident) e.getEntity();
                    new TridentTrackTask(trident).runTaskTimer(HyTrident.getInstance(), 20, 20);
                }
            }
        }
    }

}
