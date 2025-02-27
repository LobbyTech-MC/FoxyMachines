package me.gallowsdove.foxymachines.implementation.machines;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.mooy1.infinitylib.common.Scheduler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import lombok.SneakyThrows;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.utils.EmptySphereBlocks;
import me.gallowsdove.foxymachines.utils.SimpleLocation;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public final class ForcefieldDome extends SlimefunItem implements EnergyNetComponent {

    public static HashSet<Block> FORCEFIELD_BLOCKS = new HashSet<>();

    public static final int ENERGY_CONSUMPTION = 6000;

    private static final Set<Material> MATERIALS_TO_REPLACE = Set.of(Material.AIR, Material.CAVE_AIR, Material.WATER,
            Material.LAVA);

    public static ArrayList<SimpleLocation> domeLocations = new ArrayList<>();

    public static ForcefieldDome INSTANCE = new ForcefieldDome();

    public ForcefieldDome() {
        super(Items.MACHINES_ITEM_GROUP, Items.FORCEFIELD_DOME, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                Items.SWEETENED_SWEET_INGOT, Items.FORCEFIELD_STABILIZER, Items.SWEETENED_SWEET_INGOT,
                Items.FORCEFIELD_STABILIZER, Items.FORCEFIELD_ENGINE, Items.FORCEFIELD_STABILIZER,
                Items.SWEETENED_SWEET_INGOT, Items.FORCEFIELD_STABILIZER, Items.SWEETENED_SWEET_INGOT
        });

        this.addItemHandler(onTick(), onPlace(), onUse(), onBreak());
    }

    @Nonnull
    private BlockTicker onTick()
    {
        return new BlockTicker() {
            public void tick(@Nonnull Block b, @Nonnull SlimefunItem sf, @Nonnull Config data) {
                Location l = b.getLocation();

                String active = StorageCacheUtils.getData(b.getLocation(), "active");

                if (getCharge(l) <= ENERGY_CONSUMPTION && active.equals("true")) {
                    setDomeInactive(b);
                }

                if (active.equals("true")) {
                    removeCharge(l, ENERGY_CONSUMPTION);
                }
            }

            public boolean isSynchronized() {
                return true;
            }
        };
    }

    @Nonnull
    private BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(false) {
            @SneakyThrows
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                Block b = e.getBlockPlaced();
                StorageCacheUtils.setData(b.getLocation(), "owner", e.getPlayer().getUniqueId().toString());
                StorageCacheUtils.setData(b.getLocation(), "active", "false");
                StorageCacheUtils.setData(b.getLocation(), "cooldown", "false");
                domeLocations.add(new SimpleLocation(b, "forcefield"));
                // saveDomeLocations();
            }
        };
    }

    @Nonnull
    private BlockBreakHandler onBreak(){
        return new BlockBreakHandler(false, false) {
            @SneakyThrows
            @Override
            public void onPlayerBreak(@Nonnull BlockBreakEvent e, @Nonnull ItemStack item, @Nonnull List<ItemStack> list) {
                Block b = e.getBlock();
                setDomeInactive(b);
                SimpleLocation loc = new SimpleLocation(b, "forcefield");
                if (domeLocations.contains(loc)) {
                    domeLocations.remove(loc);
                    try {
						saveDomeLocations();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
            }
        };
    }

    @Nonnull
    public BlockUseHandler onUse() {
        return e -> {
            if (!SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getItemInMainHand(), Items.REMOTE_CONTROLLER, true, false)) {
                Block b = e.getClickedBlock().get();
                if (StorageCacheUtils.getData(b.getLocation(), "cooldown").equals("false")) {
                    String active = StorageCacheUtils.getData(b.getLocation(), "active");
                    if (active.equals("false")) {
                        if (getCharge(b.getLocation()) >= ENERGY_CONSUMPTION) {
                            setDomeActive(b);
                            e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "已激活穹顶力场");

                            StorageCacheUtils.setData(b.getLocation(), "cooldown", "true");
                            Scheduler.runAsync(200, () ->
                                    StorageCacheUtils.setData(b.getLocation(), "cooldown", "false"));
                        } else {
                            e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "电力不足");
                        }
                    } else {
                        setDomeInactive(b);
                        e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "已关闭穹顶力场");

                        StorageCacheUtils.setData(b.getLocation(), "cooldown", "true");
                        Scheduler.runAsync(200, () ->
                                StorageCacheUtils.setData(b.getLocation(), "cooldown", "false"));
                    }
                } else {
                    e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "你必须等待至少10秒才能再次激活穹顶力场");
                }
                e.cancel();
            }
        };
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return ENERGY_CONSUMPTION * 4;
    }

    private void setDomeActive(@Nonnull Block b) {
        ArrayList<Block> domeBlocks = EmptySphereBlocks.get(b, 32);

        for (Block block : domeBlocks) {
            UUID uuid = UUID.fromString(StorageCacheUtils.getData(b.getLocation(), "owner"));
            if (Slimefun.getProtectionManager().hasPermission(Bukkit.getOfflinePlayer(uuid), block, Interaction.BREAK_BLOCK)) {
                if (MATERIALS_TO_REPLACE.contains(block.getType())) {
                    block.setType(Material.BARRIER);
                } else if (block.getType() != Material.BARRIER) {
                    FORCEFIELD_BLOCKS.add(block);
                }
            }
        }
        StorageCacheUtils.setData(b.getLocation(), "active", "true");
    }

    private void setDomeInactive(@Nonnull Block b) {
        ArrayList<Block> domeBlocks = EmptySphereBlocks.get(b, 32);

        for(Block block: domeBlocks) {
            UUID uuid = UUID.fromString(StorageCacheUtils.getData(b.getLocation(), "owner"));
            if (Slimefun.getProtectionManager().hasPermission(Bukkit.getOfflinePlayer(uuid), block, Interaction.BREAK_BLOCK)) {
                if (block.getType() == Material.BARRIER) {
                    block.setType(Material.AIR);
                } else {
                    FORCEFIELD_BLOCKS.remove(block);
                }
            }
        }
        StorageCacheUtils.setData(b.getLocation(), "active", "false");
    }

    public void switchActive(@Nonnull Block b, @Nonnull Player p) {
        if (StorageCacheUtils.getData(b.getLocation(), "cooldown").equals("false")) {
            String active = StorageCacheUtils.getData(b.getLocation(), "active");
            if (active.equals("false")) {
                if (getCharge(b.getLocation()) >= ENERGY_CONSUMPTION) {
                    setDomeActive(b);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "穹顶力场已激活.");

                    StorageCacheUtils.setData(b.getLocation(), "cooldown", "true");
                    Scheduler.runAsync(200, () ->
                            StorageCacheUtils.setData(b.getLocation(), "cooldown", "false"));
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "电力不足");
                }
            } else {
                setDomeInactive(b);
                p.sendMessage(ChatColor.LIGHT_PURPLE + "穹顶力场已关闭");

                StorageCacheUtils.setData(b.getLocation(), "cooldown", "true");
                Scheduler.runAsync(200, () ->
                        StorageCacheUtils.setData(b.getLocation(), "cooldown", "false"));
            }
        } else {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "你必须等待至少10秒才能再次启用穹顶力场");
        }
    }

    public void setupDomes() {
        for (SimpleLocation loc: domeLocations) {
            World w = Bukkit.getServer().getWorld(UUID.fromString(loc.getWorldUUID()));
            if (w == null) {
                domeLocations.remove(loc);
                continue;
            }
            Block b = w.getBlockAt(loc.getX(), loc.getY(), loc.getZ());
            if (StorageCacheUtils.getData(b.getLocation(), "active").equals("true")) {
                setDomeActive(b);
            }
            StorageCacheUtils.setData(b.getLocation(), "cooldown", "false");
        }
    }


    public static void saveDomeLocations() throws IOException {
        Gson gson = new Gson();

        String pluginFolder = FoxyMachines.getInstance().folderPath;

        File file = new File(pluginFolder + File.separator + "domedata");
        File filePath = new File(pluginFolder);

        filePath.mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        writer.write(gson.toJson(ForcefieldDome.domeLocations));
        writer.close();
    }

    public static void loadDomeLocations() throws IOException {
        Gson gson = new Gson();

        File file = new File(FoxyMachines.getInstance().folderPath + "domedata");
        File filePath = new File(FoxyMachines.getInstance().folderPath);

        filePath.mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String json = reader.readLine();
        reader.close();

        Type type = new TypeToken<ArrayList<SimpleLocation>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;}.getType();
        ForcefieldDome.domeLocations = gson.fromJson(json, type);

        if (ForcefieldDome.domeLocations == null) {
            ForcefieldDome.domeLocations = new ArrayList<>();
        }
    }

}

