package com.hysteria.hytrident;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class TridentRecipe {

    public TridentRecipe(HyTrident main){
        if(main.getConfig().getBoolean("recipe.enable")){
            Bukkit.addRecipe(getRecipe());
        }
    }

    public ShapedRecipe getRecipe(){
        NamespacedKey key = new NamespacedKey(HyTrident.getInstance(), "hytrident");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.TRIDENT));
        recipe.shape(HyTrident.getInstance().getConfig().getString("recipe.shape.UPPER"),HyTrident.getInstance().getConfig().getString("recipe.shape.MIDDLE"),HyTrident.getInstance().getConfig().getString("recipe.shape.LOWER"));
        recipe.setIngredient('A', Material.valueOf(HyTrident.getInstance().getConfig().getString("recipe.ingredients.A")));
        recipe.setIngredient('B', Material.valueOf(HyTrident.getInstance().getConfig().getString("recipe.ingredients.B")));
        recipe.setIngredient('C', Material.valueOf(HyTrident.getInstance().getConfig().getString("recipe.ingredients.C")));
        return recipe;
    }

}
