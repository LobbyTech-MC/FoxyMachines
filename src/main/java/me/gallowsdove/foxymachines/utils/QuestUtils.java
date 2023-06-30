package me.gallowsdove.foxymachines.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import lombok.Getter;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class QuestUtils {
    private QuestUtils() {}

    public static final NamespacedKey KEY = new NamespacedKey(FoxyMachines.getInstance(), "quest");

    private static final List<EntityType> QUEST_MOBS = new ArrayList<>();
    private static final List<Line> CURSED_LINES = List.of(
            new Line("我要杀了一个 ", ", 多么美味!"),
            new Line("给我一个 ", ", 快!"),
            new Line("你一定可以帮我杀了一个 ", "."),
            new Line("我想要血....  ", " 的血."),
            new Line("我需要一个 ", " 的肝脏."),
            new Line("我听说 ", " 的血非常美味..."),
            new Line("我要", " 的血, 啊啊..."),
            new Line("我会为了 ", " 的肉杀了上帝."),
            new Line("我可以吞噬一个 ", " 随时随地."),
            new Line("我已经等待不急了. 迫不及待要杀了一个 ", "."),
            new Line("", "的血要溢出来"),
            new Line("我的诅咒将吞噬 ", "的灵魂"));
    private static final List<Line> CELESTIAL_LINES = List.of(
            new Line("我爱所有众生...除了 ", ", 我讨厌那些."),
            new Line("所有的生活都必须平衡, 那就是为什么我需要杀死一个 ", "."),
            new Line("我是天使, 同时我也是一把剑. 现在给我一个 ", "."),
            new Line("抱歉, 但请给我一些 ", ". 没有问题."),
            new Line("天使之剑需要一个祭祀品. 一个 ", "."),
            new Line("我的下一个目标是 ", ", 正如上帝的旨意."),
            new Line("下一个是 ... ", "!"),
            new Line("上帝想要 ", " 死."),
            new Line("为了上帝和荣耀, 去杀了一个 ", "."),
            new Line("去, 拿下 ", "! 为了正义!"),
            new Line("星星刚刚亮起. 我清楚地知道 ", " 会死在我的刀下"));


    public static void init() {
        if (!QUEST_MOBS.isEmpty()) {
            FoxyMachines.log(Level.WARNING, "Attempted to initialize QuestUtils after already initialized!");
            return;
        }

        for (String questMob : FoxyMachines.getInstance().getConfig().getStringList("quest-mobs")) {
            try {
                QuestUtils.QUEST_MOBS.add(EntityType.valueOf(questMob));
            } catch (IllegalArgumentException ignored) {
                FoxyMachines.log(Level.WARNING, "Invalid Entity Type in \"quest-mobs\": " + questMob);
            }
        }
    }

    @ParametersAreNonnullByDefault
    public static boolean hasActiveQuest(Player p) {
        return p.getPersistentDataContainer().has(KEY, PersistentDataType.INTEGER);
    }

    @ParametersAreNonnullByDefault
    public static boolean isQuestEntity(Player p, LivingEntity e) {
        return hasActiveQuest(p) && toEntityType(p, getQuestLine(p)) == e.getType();
    }

    @ParametersAreNonnullByDefault
    public static int getQuestLine(Player p) {
        PersistentDataContainer container = p.getPersistentDataContainer();
        int id;

        if (container.has(KEY, PersistentDataType.INTEGER)) {
            id = container.get(KEY, PersistentDataType.INTEGER);
        } else {
            id = nextQuestLine(p);
        }

        return id;
    }

    @ParametersAreNonnullByDefault
    public static int nextQuestLine(Player p) {
        int id = ThreadLocalRandom.current().nextInt(QUEST_MOBS.size());
        p.getPersistentDataContainer().set(KEY, PersistentDataType.INTEGER, id);
        return id;
    }

    @ParametersAreNonnullByDefault
    public static void sendQuestLine(Player p, SlimefunItemStack item) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int id = getQuestLine(p);

        if (item == Items.CURSED_SWORD) {
            int i = random.nextInt(CURSED_LINES.size());
            Line line = CURSED_LINES.get(i);
            p.sendMessage(ChatColor.RED + line.firstHalf() + toString(p, id) + line.secondHalf());
        } else if (item == Items.CELESTIAL_SWORD) {
            int i = random.nextInt(CELESTIAL_LINES.size());
            Line line = CELESTIAL_LINES.get(i);
            p.sendMessage(ChatColor.YELLOW + line.firstHalf() + toString(p, id) + line.secondHalf());
        }
    }

    @ParametersAreNonnullByDefault
    public static void resetQuestLine(Player p) {
        PersistentDataContainer container = p.getPersistentDataContainer();

        if (container.has(KEY, PersistentDataType.INTEGER)) {
            container.remove(KEY);
        }
    }

    public static EntityType toEntityType(Player p, int id) {
        if (id >= QUEST_MOBS.size()) {
            id = nextQuestLine(p);
        }

        return QUEST_MOBS.get(id);
    }

    public static String toString(Player p, int id) {
        if (id >= QUEST_MOBS.size()) {
            id = nextQuestLine(p);
        }

        return ChatUtils.humanize(QUEST_MOBS.get(id).name().toLowerCase());
    }
}

record Line(@Getter String firstHalf, @Getter String secondHalf) { }
