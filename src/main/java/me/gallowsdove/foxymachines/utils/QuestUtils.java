package me.gallowsdove.foxymachines.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import net.guizhanss.guizhanlib.minecraft.helper.entity.EntityTypeHelper;
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
            new Line("我很想杀一个 {entity}", ", 真的很美味!"),
            new Line("马上给我一个 {entity}", "!"),
            new Line("你一定能帮我杀一个 {entity}", "。"),
            new Line("我渴望鲜血....  ", "{entity} 的鲜血。"),
            new Line("我需要", "{entity} 的肝脏!"),
            new Line("我听说", "{entity} 的血液很鲜美..."),
            new Line("", "{entity} 的心脏, hmmm..."),
            new Line("我会因为", "{entity} 的肉而杀了上帝。"),
            new Line("我可能会食用 {entity}", "一整天。"),
            new Line("我等待这一天很久了，是时候去击杀一个 {entity}", "了。"),
            new Line("{entity} 的", "血流成河!"),
            new Line("我的诅咒将吞噬", "{entity} 的灵魂!"));
    private static final List<Line> CELESTIAL_LINES = List.of(
            new Line("我热爱所有生命，除了 {entity} ", "，我讨厌它们!"),
            new Line("所有必须平衡，这就是为什么我需要击杀一个", {entity}"。"),
            new Line("我来自天界，但我也是一把剑。现在，给我一个 {entity}", "。"),
            new Line("抱歉，但现在请给我一个 {entity}", "。不要质疑我!"),
            new Line("天界之剑需要获得献祭，", "一个 {entity} !"),
            new Line("下一位受害者是 {entity}", "，如上帝所愿。"),
            new Line("接下来是... ", "{entity}!"),
            new Line("上帝想要 {entity}", "死。"),
            new Line("为了上帝和荣耀，去杀死一只 {entity}", "。"),
            new Line("去吧，为了正义! 杀死一只 {entity}", "!"),
            new Line("众星云集，我可以看到 {entity}", "将被我的剑刃杀死。"));


    public static void init() {
        if (!QUEST_MOBS.isEmpty()) {
            FoxyMachines.log(Level.WARNING, "Attempted to initialize QuestUtils after already initialized!");
            return;
        }

        for (String questMob : FoxyMachines.getInstance().getConfig().getStringList("quest-mobs")) {
            try {
                QuestUtils.QUEST_MOBS.add(EntityType.valueOf(questMob));
            } catch (IllegalArgumentException ignored) {
                FoxyMachines.log(Level.WARNING, "\"quest-mobs\" 包含无效的生物类型: " + questMob);
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
        String entity = toString(p, getQuestLine(p));

        if (item == Items.CURSED_SWORD) {
            int i = random.nextInt(CURSED_LINES.size());
            String line = CURSED_LINES.get(i).replace("{entity}", entity);
            p.sendMessage(ChatColor.RED + line);
        } else if (item == Items.CELESTIAL_SWORD) {
            int i = random.nextInt(CELESTIAL_LINES.size());
            String line = CELESTIAL_LINES.get(i).replace("{entity}", entity);
            p.sendMessage(ChatColor.YELLOW + line);
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

        return EntityTypeHelper.getName(QUEST_MOBS.get(id));
    }
}