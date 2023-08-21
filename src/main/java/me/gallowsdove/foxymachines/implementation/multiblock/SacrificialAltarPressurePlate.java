package me.gallowsdove.foxymachines.implementation.multiblock;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.gallowsdove.foxymachines.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class SacrificialAltarPressurePlate extends SlimefunItem {
    public SacrificialAltarPressurePlate() {
        super(Items.ALTAR_ITEM_GROUP, Items.SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                new ItemStack(Material.GHAST_TEAR), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.GHAST_TEAR),
                SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.POLISHED_BLACKSTONE_PRESSURE_PLATE), SlimefunItems.MAGIC_LUMP_3,
                new ItemStack(Material.GHAST_TEAR), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.GHAST_TEAR)
        });
    }

    @Override
    public void preRegister() {
        addItemHandler(onPlace(), onUse(), onBreak());
    }

    private BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(false) {

            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                Block b = e.getBlockPlaced();
                if (isComplete(b)) {
                    StorageCacheUtils.setData(b.getLocation(), "complete", "true");
                    e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "献祭祭坛已激活");
                } else {
                    StorageCacheUtils.setData(b.getLocation(), "complete", "false");
                    e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "搭建完成献祭祭坛后点击献祭席来激活献祭祭坛");
                }
            }
        };
    }

    private BlockUseHandler onUse() {
        return e -> {
            Block b = e.getClickedBlock().get();
            if (StorageCacheUtils.getData(b.getLocation(), "complete").equals("false")) {
                if (isComplete(b)) {
                    StorageCacheUtils.setData(b.getLocation(), "complete", "true");
                    e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "献祭祭坛已激活");
                } else {
                    StorageCacheUtils.setData(b.getLocation(), "complete", "false");
                    e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "献祭祭坛未搭建完成");
                }
            }

            e.cancel();
        };
    }

    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@Nonnull BlockBreakEvent e, @Nonnull ItemStack item, @Nonnull List<ItemStack> drops) {
                StorageCacheUtils.removeData(e.getBlock().getLocation(), "complete");
                Slimefun.getDatabaseManager().getBlockDataController().removeBlock(e.getBlock().getLocation());
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "献祭祭坛已损坏");
            }
        };
    }

    private boolean isComplete(@Nonnull Block b) {

        if (b.getRelative(1, 1, 1).getType() != Material.POLISHED_BLACKSTONE_BRICK_STAIRS || !isAltarPiece(b.getRelative(1, 1, 1)) ||
                b.getRelative(-1, 1, 1).getType() != Material.POLISHED_BLACKSTONE_BRICK_STAIRS || !isAltarPiece(b.getRelative(-1, 1, 1)) ||
                b.getRelative(1, 1, -1).getType() != Material.POLISHED_BLACKSTONE_BRICK_STAIRS || !isAltarPiece(b.getRelative(1, 1, -1)) ||
            b.getRelative(-1, 1, -1).getType() != Material.POLISHED_BLACKSTONE_BRICK_STAIRS || !isAltarPiece(b.getRelative(-1, 1, -1))) {
            return false;
        }

        if (b.getRelative(1, 0, 1).getType() != Material.POLISHED_BLACKSTONE_BRICKS || !isAltarPiece(b.getRelative(1, 0, 1)) ||
                b.getRelative(-1, 0, 1).getType() != Material.POLISHED_BLACKSTONE_BRICKS || !isAltarPiece(b.getRelative(-1, 0, 1)) ||
                b.getRelative(1, 0, -1).getType() != Material.POLISHED_BLACKSTONE_BRICKS || !isAltarPiece(b.getRelative(1, 0, -1)) ||
                b.getRelative(-1, 0, -1).getType() != Material.POLISHED_BLACKSTONE_BRICKS || !isAltarPiece(b.getRelative(-1, 0, -1))) {
            return false;
        }

        if (b.getRelative(0, 1, 1).getType() != Material.SOUL_TORCH || !isAltarPiece(b.getRelative(0, 1, 1)) ||
                b.getRelative(0, 1, -1).getType() != Material.SOUL_TORCH || !isAltarPiece(b.getRelative(0, 1, -1)) ||
                b.getRelative(1, 1, 0).getType() != Material.SOUL_TORCH || !isAltarPiece(b.getRelative(1, 1, 0)) ||
                b.getRelative(-1, 1, 0).getType() != Material.SOUL_TORCH || !isAltarPiece(b.getRelative(-1, 1, 0))) {
            return false;
        }

        if (b.getRelative(0, 0, 1).getType() != Material.POLISHED_BLACKSTONE_BRICK_WALL || !isAltarPiece(b.getRelative(0, 0, 1)) ||
                b.getRelative(0, 0, -1).getType() != Material.POLISHED_BLACKSTONE_BRICK_WALL || !isAltarPiece(b.getRelative(0, 0, -1)) ||
                b.getRelative(1, 0, 0).getType() != Material.POLISHED_BLACKSTONE_BRICK_WALL || !isAltarPiece(b.getRelative(1, 0, 0)) ||
                b.getRelative(-1, 0, 0).getType() != Material.POLISHED_BLACKSTONE_BRICK_WALL || !isAltarPiece(b.getRelative(-1, 0, 0))) {
            return false;
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (b.getRelative(x, -1, z).getType() != Material.POLISHED_BLACKSTONE_BRICKS || !isAltarPiece(b.getRelative(x, -1, z))) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isAltarPiece(@Nonnull Block b) {
        if (StorageCacheUtils.getData(b.getLocation(), "id") == null) {
            return false;
        }

        return switch (StorageCacheUtils.getData(b.getLocation(), "id")) {
            case "SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS", "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL", "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS", "SACRIFICIAL_ALTAR_SOUL_TORCH" -> true;
            default -> false;
        };
    }
}