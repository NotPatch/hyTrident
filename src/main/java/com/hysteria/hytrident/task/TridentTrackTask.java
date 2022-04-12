package com.hysteria.hytrident.task;

import com.hysteria.hytrident.HyTrident;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TridentTrackTask extends BukkitRunnable {

    private Trident trident;
    private Player player;
    private ArrayList<Chunk> loadedChunks = new ArrayList<>();

    public TridentTrackTask(Trident trident){
        this.trident = trident;
        this.player = (Player) trident.getShooter();
    }

    @Override
    public void run() {
        if(trident.getLocation().getY() < -63){
            player.sendMessage(HyTrident.getInstance().getLanguage().getLastTridentLocation(trident));
            cancel();
        }
        if(trident.isInBlock()){
            player.sendMessage(HyTrident.getInstance().getLanguage().getTridentLocation(trident));
            loadedChunks.forEach(chunk ->{
                if(getPlayersInChunk(chunk) < 1){
                    chunk.unload();
                }
            });
            cancel();

        }else{
            Chunk chunk = trident.getLocation().getChunk();
            if(!chunk.isLoaded()){
                loadedChunks.add(chunk);
                chunk.load();
            }
        }
    }

    public int getPlayersInChunk(Chunk chunk){
        int count = 0;
        for(Entity entity : chunk.getEntities()){
            if(entity instanceof Player){
                count++; 
            }
        }
        return count;
    }

}
