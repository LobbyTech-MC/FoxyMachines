package me.gallowsdove.foxymachines.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.Getter;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import net.guizhanss.guizhanlib.minecraft.helper.entity.EntityTypeHelper;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuestUtils {
    public static NamespacedKey KEY = new NamespacedKey(FoxyMachines.getInstance(), "quest");

    private static final List<Line> CURSED_LINES = List.of(
            new Line("我很想杀一个", ", 真的很美味!"),
            new Line("马上!给我一个", "!"),
            new Line("你一定能帮我杀一个", "。"),
            new Line("我渴望鲜血....  ", "的鲜血。"),
            new Line("我需要", "的肝脏!"),
            new Line("我听说", "的血液很鲜美..."),
            new Line("", "心脏, hmmm..."),
            new Line("我会因为", "肉而杀了上帝。"),
            new Line("我可能会食用", "一整天。"),
            new Line("我等待这一天很久了，是时候去击杀一个", "了。"),
            new Line("", "血流成河!"),
            new Line("我的诅咒将吞噬", "的灵魂!"));
    private static final List<Line> CELESTIAL_LINES = List.of(
            new Line("我热爱所有生命，除了", "，我讨厌它们!"),
            new Line("所有必须平衡，这就是为什么我需要击杀一个", "。"),
            new Line("我来自天界，但我也是一把剑。现在，给我一个 ", "。"),
            new Line("抱歉，但现在请给我一个", "。不要质疑我!"),
            new Line("天界之剑需要获得献祭，", "!"),
            new Line("下一位受害者是", "，如上帝所愿。"),
            new Line("接下来是... ", "!"),
            new Line("上帝想要", "死。"),
            new Line("为了上帝和荣耀，去杀死一只", "。"),
            new Line("去吧，为了正义! 杀死一只", "!"),
            new Line("众星云集，我可以看到", "将被我的剑刃杀死。"));

    @ParametersAreNonnullByDefault
    public static void sendQuestLine(Player p, SlimefunItemStack item) {
        PersistentDataContainer container = p.getPersistentDataContainer();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int id;

        if (container.has(KEY, PersistentDataType.INTEGER)) {
            id = container.get(KEY, PersistentDataType.INTEGER);
        } else {
            id = random.nextInt(52);
            container.set(KEY, PersistentDataType.INTEGER, id);
        }

        if (item == Items.CURSED_SWORD) {
            int i = random.nextInt(CURSED_LINES.size());
            Line line = CURSED_LINES.get(i);
            p.sendMessage(ChatColor.RED + line.firstHalf() + toString(id) + line.secondHalf());
        } else if (item == Items.CELESTIAL_SWORD) {
            int i = random.nextInt(CELESTIAL_LINES.size());
            Line line = CELESTIAL_LINES.get(i);
            p.sendMessage(ChatColor.YELLOW + line.firstHalf() + toString(id) + line.secondHalf());
        }
    }

    public static EntityType toEntityType(int id) {
        Validate.isTrue(id <= 51, "实体ID不能大于51");

        return switch (id) {
            case 0 -> EntityType.BAT;
            case 1 -> EntityType.BEE;
            case 2 -> EntityType.BLAZE;
            case 3 -> EntityType.CAT;
            case 4 -> EntityType.CHICKEN;
            case 5 -> EntityType.CAVE_SPIDER;
            case 6 -> EntityType.COD;
            case 7 -> EntityType.COW;
            case 8 -> EntityType.CREEPER;
            case 9 -> EntityType.DOLPHIN;
            case 10 -> EntityType.DONKEY;
            case 11 -> EntityType.DROWNED;
            case 12 -> EntityType.ENDERMAN;
            case 13 -> EntityType.FOX;
            case 14 -> EntityType.GHAST;
            case 15 -> EntityType.GUARDIAN;
            case 16 -> EntityType.HOGLIN;
            case 17 -> EntityType.HUSK;
            case 18 -> EntityType.HORSE;
            case 19 -> EntityType.IRON_GOLEM;
            case 20 -> EntityType.LLAMA;
            case 21 -> EntityType.MAGMA_CUBE;
            case 22 -> EntityType.MUSHROOM_COW;
            case 23 -> EntityType.OCELOT;
            case 24 -> EntityType.PANDA;
            case 25 -> EntityType.PARROT;
            case 26 -> EntityType.PHANTOM;
            case 27 -> EntityType.PIG;
            case 28 -> EntityType.PIGLIN;
            case 29 -> EntityType.PIGLIN_BRUTE;
            case 30 -> EntityType.PILLAGER;
            case 31 -> EntityType.POLAR_BEAR;
            case 32 -> EntityType.PUFFERFISH;
            case 33 -> EntityType.RABBIT;
            case 34 -> EntityType.SALMON;
            case 35 -> EntityType.SHEEP;
            case 36 -> EntityType.SILVERFISH;
            case 37 -> EntityType.SKELETON;
            case 38 -> EntityType.SLIME;
            case 39 -> EntityType.SNOWMAN;
            case 40 -> EntityType.SPIDER;
            case 41 -> EntityType.SQUID;
            case 42 -> EntityType.STRAY;
            case 43 -> EntityType.STRIDER;
            case 44 -> EntityType.TURTLE;
            case 45 -> EntityType.TROPICAL_FISH;
            case 46 -> EntityType.WITCH;
            case 47 -> EntityType.WITHER_SKELETON;
            case 48 -> EntityType.WOLF;
            case 49 -> EntityType.ZOGLIN;
            case 50 -> EntityType.ZOMBIE;
            case 51 -> EntityType.ZOMBIFIED_PIGLIN;
            default -> EntityType.FOX;
        };
    }

    public static String toString(int id) {
        Validate.isTrue(id <= 51, "实体ID不能大于51");

        return EntityTypeHelper.getName(toEntityType(id));
    }
}

record Line(@Getter String firstHalf, @Getter String secondHalf) { }
