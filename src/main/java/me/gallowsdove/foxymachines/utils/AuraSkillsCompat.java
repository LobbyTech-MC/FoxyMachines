package me.gallowsdove.foxymachines.utils;

import org.bukkit.block.Block;

import dev.aurelium.auraskills.api.AuraSkillsBukkitProvider;
import dev.aurelium.auraskills.api.region.Regions;

public class AuraSkillsCompat {
    public static void addPlacedBlock(Block block) {
        Regions regions = AuraSkillsBukkitProvider.getInstance().getRegions();
        if (regions != null) {
            regions.addPlacedBlock(block);
        }
    }
}
