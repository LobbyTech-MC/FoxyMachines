package me.gallowsdove.foxymachines.implementation.materials;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;

public class SimpleRadioactiveMaterial extends SimpleMaterial implements Radioactive {
    private final Radioactivity radioactivity;

    @ParametersAreNonnullByDefault
    public SimpleRadioactiveMaterial(SlimefunItemStack item, RecipeType type, ItemStack[] recipe, int amount, Radioactivity radioactivity) {
        super(item, type, recipe, amount);

        this.radioactivity = radioactivity;
    }

    @Override @Nonnull
    public Radioactivity getRadioactivity() {return this.radioactivity;}
}
