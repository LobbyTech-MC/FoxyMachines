package me.gallowsdove.foxymachines.types;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.gallowsdove.foxymachines.FoxyMachines;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

public class FoxyRecipeType {
    private static final CustomItemStack QUEST_ITEM = new CustomItemStack(new CustomItemStack(Material.MOJANG_BANNER_PATTERN, "&6任务奖励", "", "&e&o使用特殊的剑完成任务获得奖励",
            "&e&o使用 &c/foxy quest &e&o查看当前任务"));
    static {
        ItemMeta meta = QUEST_ITEM.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        QUEST_ITEM.setItemMeta(meta);
    }

    public static RecipeType SACRIFICIAL_ALTAR = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "sacrificial_altar"),
            new CustomItemStack(Material.POLISHED_BLACKSTONE_BRICKS, "&c魔法祭坛", "", "&e&o在魔法祭坛处理生物",
                    "&e&o使用 &c/foxy altar &e&o查看多方块机器"));
    public static RecipeType FISHING = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "fishing"),
            new CustomItemStack(Material.FISHING_ROD, "&b钓鱼", "", "&e&o钓鱼获得特殊物品"));
    public static RecipeType QUEST = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "quest"), QUEST_ITEM);
    public static RecipeType CUSTOM_MOB_DROP = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "mob_drop"),
            new CustomItemStack(Material.DIAMOND_SWORD, "&a独特的怪物精华", "", "&e&o击杀特殊怪物获得"));
}
