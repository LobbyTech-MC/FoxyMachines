package me.gallowsdove.foxymachines;

import io.github.mooy1.infinitylib.machines.MachineLore;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;
import me.gallowsdove.foxymachines.implementation.machines.ElectricGoldRefinery;
import me.gallowsdove.foxymachines.implementation.machines.ForcefieldDome;
import me.gallowsdove.foxymachines.implementation.machines.ImprovementForge;
import me.gallowsdove.foxymachines.implementation.machines.PotionMixer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

public final class Items{

    // Item groups
    public static NestedItemGroup MAIN_ITEM_GROUP = new NestedItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "foxy_machines"),
            new CustomItemStack(Material.SHEARS, "&4魔法机器", "", "&a> 点击打开"));

    public static SubItemGroup MATERIALS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "materials"),
            MAIN_ITEM_GROUP,
            new CustomItemStack(Material.GOLD_INGOT, "&bMaterials")
    );

    public static SubItemGroup MACHINES_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "machines"),
            MAIN_ITEM_GROUP,
            new CustomItemStack(Material.BEACON, "&aMachines")
    );

    public static SubItemGroup TOOLS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "tools"),
            MAIN_ITEM_GROUP,
            new CustomItemStack(Material.BLAZE_ROD, "&eTools")
    );

    public static SubItemGroup WEAPONS_AND_ARMORS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "weapons_and_armors"),
            MAIN_ITEM_GROUP,
            new CustomItemStack(Material.NETHERITE_SWORD, "&aWeapons and Armors")
    );

    public static SubItemGroup ALTAR_ITEM_GROUP = new SubItemGroup(
        new NamespacedKey(FoxyMachines.getInstance(), "sacrificial_altars"),
        MAIN_ITEM_GROUP,
        new CustomItemStack(Material.POLISHED_BLACKSTONE_BRICKS, "&4Sacrificial Altar")
    );

    public static SubItemGroup BOSSES_ITEM_GROUP = new SubItemGroup(
        new NamespacedKey(FoxyMachines.getInstance(), "bosses"),
        MAIN_ITEM_GROUP,
        new CustomItemStack(Material.DRAGON_HEAD, "&cBosses")
    );

    public static SubItemGroup GHOST_BLOCKS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "ghost_blocks"),
            MAIN_ITEM_GROUP, new CustomItemStack(Material.GLASS, "&5灵魂方块", "", "&a> 点击打开"));

    // Items
    public static final SlimefunItemStack ELECTRIC_WIND_STAFF = new SlimefunItemStack(
            "ELECTRIC_WIND_STAFF",
            Material.BLAZE_ROD,
            "&b电力风法杖",
            "",
            "&7乘风而行",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 100 J"
    );

    static {
        ELECTRIC_WIND_STAFF.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = ELECTRIC_WIND_STAFF.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_WIND_STAFF.setItemMeta(meta);
    }

    public static final SlimefunItemStack ELECTRIC_FIRE_STAFF = new SlimefunItemStack(
            "ELECTRIC_FIRE_STAFF",
            Material.BLAZE_ROD,
            "&4电力火法杖",
            "",
            "&7制造火海",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 100 J"
    );
    static {
        ELECTRIC_FIRE_STAFF.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = ELECTRIC_FIRE_STAFF.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_FIRE_STAFF.setItemMeta(meta);
    }

    public static final SlimefunItemStack ELECTRIC_FIRE_STAFF_II = new SlimefunItemStack(
            "ELECTRIC_FIRE_STAFF_II",
            Material.BLAZE_ROD,
            "&4电力火法杖 &7- &eII",
            "",
            "&7火, 火, 更多火!",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 200 J"
    );
    static {
        ELECTRIC_FIRE_STAFF_II.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = ELECTRIC_FIRE_STAFF_II.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_FIRE_STAFF_II.setItemMeta(meta);
    }

    public static final SlimefunItemStack HEALING_BOW = new SlimefunItemStack(
            "HEALING_BOW",
            Material.BOW,
            "&4治疗弓",
            "&c治愈 II",
            "",
            "&8终于有支援武器了"
    );

    public static final SlimefunItemStack REINFORCED_STRING = new SlimefunItemStack(
            "REINFORCED_STRING",
            Material.STRING,
            "&b强化合金线"
    );

    public static final SlimefunItemStack IMPROVEMENT_FORGE = new SlimefunItemStack(
            "IMPROVEMENT_FORGE",
            Material.SMITHING_TABLE,
            "&b改进型铁砧",
            "",
            "&7用于升级粘液科技武器装备",
            "",
            LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
            LoreBuilder.powerPerSecond(ImprovementForge.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack IMPROVEMENT_CORE = new SlimefunItemStack(
            "IMPROVEMENT_CORE",
            "faff2eb498e5c6a04484f0c9f785b448479ab213df95ec91176a308a12add70",
            "&a改进型芯片",
            "",
            "&7使用改进型铁砧所需材料"
    );

    public static final SlimefunItemStack POTION_MIXER = new SlimefunItemStack(
            "POTION_MIXER",
            Material.BREWING_STAND	,
            "&b药水叠加器",
            "",
            "&7用于叠加药水效果",
            "",
            LoreBuilder.machine(MachineTier.GOOD, MachineType.MACHINE),
            LoreBuilder.powerPerSecond(PotionMixer.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack ELECTRIC_GOLD_REFINERY = new SlimefunItemStack(
            "ELECTRIC_GOLD_REFINERY",
            Material.GOLD_BLOCK	,
            "&b电动炼金厂",
            "",
            "&7将金粉烧制成金锭",
            "",
            LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
            LoreBuilder.powerPerSecond(ElectricGoldRefinery.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack CHUNK_LOADER = new SlimefunItemStack(
            "CHUNK_LOADER",
            Material.BEACON,
            "&b区块加载器",
            "",
            "&7使区块保持加载"
    );

    public static final SlimefunItemStack STABILIZED_BLISTERING_BLOCK = new SlimefunItemStack(
            "STABILIZED_BLISTERING_BLOCK",
            Material.SNOW_BLOCK,
            "&b稳定型起泡锭",
            "",
            "&7一个稳定的材料"
    );

    public static final SlimefunItemStack BOOSTED_RAIL = new SlimefunItemStack(
            "BOOSTED_RAIL",
            Material.RAIL,
            "&f加速铁轨",
            "",
            "&7支持 2.5 倍速"
    );

    public static final SlimefunItemStack BOOSTED_ACTIVATOR_RAIL = new SlimefunItemStack(
            "BOOSTED_ACTIVATOR_RAIL",
            Material.ACTIVATOR_RAIL,
            "&f加速激活铁轨",
            "",
            "&7支持 2.5 倍速"
    );

    public static final SlimefunItemStack BOOSTED_DETECTOR_RAIL = new SlimefunItemStack(
            "BOOSTED_DETECTOR_RAIL",
            Material.DETECTOR_RAIL,
            "&f加速探测铁轨",
            "",
            "&7支持 2.5 倍速"
    );

    public static final SlimefunItemStack BOOSTED_POWERED_RAIL = new SlimefunItemStack(
            "BOOSTED_POWERED_RAIL",
            Material.POWERED_RAIL,
            "&f加速充能铁轨",
            "",
            "&7支持 2.5 倍速"
    );

    public static final SlimefunItemStack BERRY_BUSH_TRIMMER = new SlimefunItemStack(
            "BERRY_BUSH_TRIMMER",
            Material.SHEARS,
            "&e甜浆果灌木修建器",
            "",
            "&7取出甜浆果灌木的伤害效果"
    );

    public static final SlimefunItemStack FORCEFIELD_DOME = new SlimefunItemStack(
            "FORCEFIELD_DOME",
            Material.OBSERVER,
            "&4屏障生成器",
            "",
            "&7充能的时候",
            "&7在32格半径周围生成屏障",
            "&7一旦被破坏或者不充能",
            "&7屏障就消失",
            "",
            MachineLore.energyPerSecond(ForcefieldDome.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack REMOTE_CONTROLLER = new SlimefunItemStack(
            "REMOTE_CONTROLLER",
            Material.NAME_TAG,
            "&c遥控器",
            "",
            "&7用于遥控屏障生成器",
            "&7Shift + 右键 绑定屏障生成器",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 1000 J"
    );

    public static final SlimefunItemStack FORCEFIELD_ENGINE = new SlimefunItemStack(
            "FORCEFIELD_ENGINE",
            Material.STRUCTURE_BLOCK,
            "&f屏障引擎"
    );

    public static final SlimefunItemStack FORCEFIELD_STABILIZER = new SlimefunItemStack(
            "FORCEFIELD_STABILIZER",
            Material.STRUCTURE_VOID,
            "&f屏障稳定器"
    );

    public static final SlimefunItemStack WIRELESS_TRANSMITTER = new SlimefunItemStack(
            "WIRELESS_TRANSMITTER",
            Material.REPEATER,
            "&f无线信号发射器"
    );

    public static final SlimefunItemStack DEMONIC_INGOT = new SlimefunItemStack(
            "DEMONIC_INGOT",
            Material.GOLD_INGOT,
            "&c屏障锭"
    );

    public static final SlimefunItemStack DEMONIC_PLATE = new SlimefunItemStack(
            "DEMONIC_PLATE",
            Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
            "&c屏障塑料"
    );

    public static final SlimefunItemStack AQUATIC_NETHERITE_INGOT = new SlimefunItemStack(
            "AQUATIC_NETHERITE_INGOT",
            Material.NETHERITE_INGOT,
            "&b下界水锭"
    );

    public static final SlimefunItemStack DAMIENIUM = new SlimefunItemStack(
            "DAMIENIUM",
            Material.GOLD_INGOT,
            "&a复合锭"
    );

    public static final SlimefunItemStack SWEET_INGOT = new SlimefunItemStack(
            "SWEET_INGOT",
            Material.GOLD_INGOT,
            "&e甜心锭"
    );

    public static final SlimefunItemStack SWEETENED_SWEET_INGOT = new SlimefunItemStack(
            "SWEETENED_SWEET_INGOT",
            Material.GOLD_INGOT,
            "&e终极甜心锭"
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS",
            Material.POLISHED_BLACKSTONE_BRICKS,
            "&f魔法黑石砖",
            "",
            "&7魔法祭坛的一部分"
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL",
            Material.POLISHED_BLACKSTONE_BRICK_WALL,
            "&f魔法黑石砖墙",
            "",
            "&7魔法祭坛的一部分"
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS",
            Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
            "&f魔法黑石砖楼梯",
            "",
            "&7魔法祭坛的一部分"
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_SOUL_TORCH = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_SOUL_TORCH",
            Material.SOUL_TORCH,
            "&f魔法灵魂火把",
            "",
            "&7魔法祭坛的一部分"
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE",
            Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
            "&f魔法垫子",
            "",
            "&7魔法祭坛的一部分"
    );

    public static final SlimefunItemStack CURSED_RABBIT_PAW = new SlimefunItemStack(
            "CURSED_RABBIT_PAW",
            Material.RABBIT_FOOT,
            "&c魔法兔子脚"
    );

    public static final SlimefunItemStack HUMAN_SKULL = new SlimefunItemStack(
            "HUMAN_SKULL",
            Material.SKELETON_SKULL,
            "&c头颅"
    );

    public static final SlimefunItemStack BLOOD_INFUSED_SKULL = new SlimefunItemStack(
            "BLOOD_INFUSED_SKULL",
            "daa4e2294df370b9a50cb924cdda78f740b0fbaf5a687106178505c80a79addc",
            "&c骷髅骨"
    );

    public static final SlimefunItemStack BLOOD = new SlimefunItemStack(
            "BLOOD",
            Material.REDSTONE,
            "&c血"
    );

    public static final SlimefunItemStack UNHOLY_WITHER_SKELETON_BONE = new SlimefunItemStack(
            "UNHOLY_WITHER_SKELETON_BONE",
            Material.BONE,
            "&c凋零骷髅骨"
    );

    public static final SlimefunItemStack PURIFIED_BONE = new SlimefunItemStack(
            "PURIFIED_BONE",
            Material.BONE,
            "&b干净的骨头"
    );

    public static final SlimefunItemStack PURE_BONE_DUST = new SlimefunItemStack(
            "PURE_BONE_DUST",
            Material.BONE_MEAL,
            "&b干净的骨粉"
    );

    public static final SlimefunItemStack BUCKET_OF_BLOOD = new SlimefunItemStack(
            "BUCKET_OF_BLOOD",
            Material.LAVA_BUCKET,
            "&c一桶血"
    );

    public static final SlimefunItemStack POSEIDONS_FISHING_ROD = new SlimefunItemStack(
            "POSEIDONS_FISHING_ROD",
            Material.FISHING_ROD,
            "&b潘多拉鱼竿",
            "&7潘多拉的祝福",
            "",
            "&7可以钓上特殊物品"
    );
    static {
        POSEIDONS_FISHING_ROD.addUnsafeEnchantment(Enchantment.LUCK, 5);
        POSEIDONS_FISHING_ROD.addUnsafeEnchantment(Enchantment.LURE, 3);
    }

    public static final SlimefunItemStack POSEIDONS_BLESSING = new SlimefunItemStack(
            "POSEIDONS_BLESSING",
            Material.HEART_OF_THE_SEA,
            "&b潘多拉的祝福"
    );

    public static final SlimefunItemStack CURSED_SWORD = new SlimefunItemStack(
            "CURSED_SWORD",
            Material.NETHERITE_SWORD,
            "&c魔法剑",
            "&7生命偷取 I",
            "",
            "&7混乱敌人. 增加伤害.",
            "&7造成负面效果"
    );

    public static final SlimefunItemStack CELESTIAL_SWORD = new SlimefunItemStack(
            "CELESTIAL_SWORD",
            Material.NETHERITE_SWORD,
            "&e天使之剑",
            "&7天使剑击 II",
            "",
            "&7无视 20% of 抗性."
    );

    public static final SlimefunItemStack ELUCIDATOR = new SlimefunItemStack(
            "ELUCIDATOR",
            Material.NETHERITE_SWORD,
            "&b阐释者",
            "&7力量 III",
            "&7生命偷取 II",
            "&7治疗提升"
    );
    static {
        ELUCIDATOR.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        ELUCIDATOR.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 5);
    }

    public static final SlimefunItemStack MAGIC_LUMP_4 = new SlimefunItemStack(
            "MAGIC_LUMP_4",
            Material.GOLD_NUGGET,
            "&6魔法泵 &7- &eIV",
            "",
            "&c&o等级: IV");

    public static final SlimefunItemStack MAGIC_LUMP_5 = new SlimefunItemStack(
            "MAGIC_LUMP_5",
            Material.GOLD_NUGGET,
            "&6魔法泵 &7- &eV",
            "",
            "&c&o等级: V");

    public static final SlimefunItemStack AQUATIC_HELMET = new SlimefunItemStack(
            "AQUATIC_HELMET",
            Material.NETHERITE_HELMET,
            "&b水下头盔",
            "&7水下呼吸",
            "&7夜视"
    );
    static {
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.OXYGEN, 5);
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
    }


    public static final SlimefunItemStack RESISTANT_CHESTPLATE = new SlimefunItemStack(
            "RESISTANT_CHESTPLATE",
            Material.NETHERITE_CHESTPLATE,
            "&a抗性胸甲",
            "&7抗性提升 I",
            "&7生命恢复 I"
    );
    static {
        RESISTANT_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
    }

    public static final SlimefunItemStack FIERY_LEGGINGS = new SlimefunItemStack(
            "FIERY_LEGGINGS",
            Material.NETHERITE_LEGGINGS,
            "&c风火轮",
            "&7火焰光环 II"
    );
    static {
        FIERY_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        FIERY_LEGGINGS.addUnsafeEnchantment(Enchantment.THORNS, 6);
    }

    public static final SlimefunItemStack LIGHT_BOOTS = new SlimefunItemStack(
            "LIGHT_BOOTS",
            Material.NETHERITE_BOOTS,
            "&e灵巧护腿",
            "&7跳跃提升 I",
            "&7速度 I",
            "&7灵巧"
    );
    static {
        LIGHT_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        LIGHT_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 5);
        LIGHT_BOOTS.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 5);
    }

    public static final SlimefunItemStack AQUATIC_HELMET_FRAME = new SlimefunItemStack(
            "HELMET_FRAME",
            Material.CHAINMAIL_HELMET,
            "&f水下头盔框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack RESISTANT_CHESTPLATE_FRAME = new SlimefunItemStack(
            "RESISTANT_CHESTPLATE_FRAME",
            Material.CHAINMAIL_CHESTPLATE,
            "&f抗性胸甲框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack FIERY_LEGGINGS_FRAME = new SlimefunItemStack(
            "FIERY_LEGGINGS_FRAME",
            Material.CHAINMAIL_LEGGINGS,
            "&f风火轮框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack LIGHT_BOOTS_FRAME = new SlimefunItemStack(
            "LIGHT_BOOTS_FRAME",
            Material.CHAINMAIL_BOOTS,
            "&f灵巧护腿框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack CURSED_SHARD = new SlimefunItemStack(
            "CURSED_SHARD",
            Material.NETHERITE_SCRAP,
            "&c魔法碎片",
            "&7合成材料",
            "&7丢入祭坛后将重置任务进度..."
    );

    public static final SlimefunItemStack CELESTIAL_SHARD = new SlimefunItemStack(
            "CELESTIAL_SHARD",
            Material.PRISMARINE_SHARD,
            "&e天使碎片",
            "&7合成材料",
            "&7丢入祭坛后将重置任务进度..."
    );

    public static final SlimefunItemStack EQUANIMOUS_GEM = new SlimefunItemStack(
            "EQUANIMOUS_GEM",
            Material.EMERALD,
            "&a宝石"
    );

    public static final SlimefunItemStack POLAR_FOX_HIDE = new SlimefunItemStack(
            "POLAR_FOX_HIDE",
            Material.SNOWBALL,
            "&f北极熊皮"
    );

    public static final SlimefunItemStack MAGMA_ESSENCE = new SlimefunItemStack(
            "MAGMA_ESSENCE",
            Material.MAGMA_CREAM,
            "&c岩浆精华"
    );

    public static final SlimefunItemStack TROPICAL_FISH_SCALE = new SlimefunItemStack(
            "TROPICAL_FISH_SCALE",
            Material.TROPICAL_FISH_SPAWN_EGG,
            "&b热带鱼鳞"
    );

    public static final SlimefunItemStack PARROT_FEATHER = new SlimefunItemStack(
            "PARROT_FEATHER",
            Material.FEATHER,
            "&a鹦鹉羽毛"
    );

    public static final SlimefunItemStack UNBREAKABLE_RUNE = new SlimefunItemStack(
            "UNBREAKABLE_RUNE",
            new ColoredFireworkStar(
                    Color.fromRGB(0, 188, 0),
                    "&7古代符文 &8&l[&2&l无法破坏&8&l]",
                    "",
                    "&e把他丢到一个物品上",
                    "&e使他 &2无法破坏"
            ));

    public static final SlimefunItemStack PIXIE_QUEEN_SPAWN_EGG = new SlimefunItemStack(
            "PIXIE_QUEEN_SPAWN_EGG",
            Material.CREEPER_SPAWN_EGG,
            "&a精灵女王刷怪蛋"
    );

    public static final SlimefunItemStack HEADLESS_HORSEMAN_SPAWN_EGG = new SlimefunItemStack(
            "HEADLESS_HORSEMAN_SPAWN_EGG",
            Material.SPIDER_SPAWN_EGG,
            "&c死亡骑士刷怪蛋"
    );

    public static final SlimefunItemStack PIXIE_QUEEN_HEART = new SlimefunItemStack(
            "PIXIE_QUEEN_HEART",
            Material.FERMENTED_SPIDER_EYE,
            "&4精灵女王之心"
    );

    public static final SlimefunItemStack PIXIE_DUST = new SlimefunItemStack(
            "PIXIE_DUST",
            Material.SUGAR,
            "&e精灵药水",
            "",
            "&7力量 IV"
    );

    public static final SlimefunItemStack VILE_PUMPKIN = new SlimefunItemStack(
            "VILE_PUMPKIN",
            Material.CARVED_PUMPKIN,
            "&c魔法南瓜"
    );

    public static final SlimefunItemStack VILE_SEEDS = new SlimefunItemStack(
            "VILE_SEEDS",
            Material.MELON_SEEDS,
            "&c魔法种子",
            "",
            "&7生命提升 V"
    );

    public static final SlimefunItemStack ACRI_ARCUM = new SlimefunItemStack(
            "ACRI_ARCUM",
            Material.BOW,
            "&e锋利的弓",
            "",
            "&7伤害 III",
            "&7盔甲穿透 II"
    );
    static {
        ACRI_ARCUM.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 7);
        ACRI_ARCUM.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    }

    public static final SlimefunItemStack GHOST_BLOCK_REMOVER = new SlimefunItemStack(
            "GHOST_BLOCK_REMOVER",
            Material.CLOCK,
            "&e灵魂方块移除器",
            "",
            "&7右键移除一个灵魂方块"
    );

    public static final SlimefunItemStack POSITION_SELECTOR = new SlimefunItemStack(
            "POSITION_SELECTOR",
            Material.STICK,
            "&ePosition Selector",
            "",
            "&7Left click a block to select primary position.",
            "&7Right click a block to select secondary position.",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 200 J"
    );

    public static final SlimefunItemStack FILL_WAND = new SlimefunItemStack(
            "FILL_WAND",
            Material.BLAZE_ROD,
            "&eFill Wand",
            "",
            "&7Select corner points with Position Selector.",
            "&7Shift right click to select material.",
            "&7Right click to fill an area.",
            "",
            "&7Material: &eNone",
            "&c&o&8\u21E8 &e\u26A1 &70 / 1000 J"
    );

    public static final SlimefunItemStack SPONGE_WAND = new SlimefunItemStack(
            "SPONGE_WAND",
            Material.BLAZE_ROD,
            "&eSponge Wand",
            "",
            "&7Select corner points with Position Selector.",
            "&7Right click to remove Water and Lava.",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 2000 J"
    );

    public static final SlimefunItemStack NUCLEAR_SALT = new SlimefunItemStack(
            "NUCLEAR_SALT",
            Material.LIME_DYE,
            "&aNuclear Salt",
            "",
            LoreBuilder.radioactive(Radioactivity.VERY_HIGH)
    );

    public static final SlimefunItemStack COMPRESSED_SPONGE = new SlimefunItemStack(
            "COMPRESSED_SPONGE",
            Material.WET_SPONGE,
            "&fCompressed Sponge"
    );
}
