package me.gallowsdove.foxymachines.implementation.multiblock;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.gallowsdove.foxymachines.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class SacrificialAltarPiece extends SlimefunItem {
    public SacrificialAltarPiece(@Nonnull SlimefunItemStack item, @Nonnull ItemStack[] recipe, int amount) {
        super(Items.ALTAR_ITEM_GROUP, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe, new SlimefunItemStack(item, amount));
    }

    @Override
    public void preRegister() {
        addItemHandler(onBlockBreak());
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @ParametersAreNonnullByDefault
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Block b = findAltar(e.getBlock());

                if (b != null) {
                    if (StorageCacheUtils.getData(b.getLocation(), "complete") != null &&
                            StorageCacheUtils.getData(b.getLocation(), "complete").equals("true")) {
                        StorageCacheUtils.setData(b.getLocation(), "complete", "false");
                        e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "献祭祭坛不完整，请修复献祭祭坛后重新激活");
                    }
                }

                Slimefun.getDatabaseManager().getBlockDataController().removeBlock(e.getBlock().getLocation());
            }
        };
    }

    @Nullable
    private Block findAltar(@Nonnull Block b) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }

                    Block block = b.getRelative(x, y, z);
                    SlimefunBlockData blockData = StorageCacheUtils.getBlock(block.getLocation());

                    if (block.getType() == Material.POLISHED_BLACKSTONE_PRESSURE_PLATE &&
                        blockData != null &&
                        blockData.getSfId().equals("SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE")) {
                        return block;
                    }
                }
            }
        }

        return null;
    }
}
