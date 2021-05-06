package me.gallowsdove.foxymachines.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import lombok.Getter;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class QuestUtils {
    public static NamespacedKey KEY = new NamespacedKey(FoxyMachines.getInstance(), "quest");

    private static final List<Line> CURSED_LINES = List.of(
            new Line("I would love to kill a ", ", so tasty!"),
            new Line("Give me a ", ", now!"),
            new Line("Surely you can help me slay a ", "."),
            new Line("I want blood....  ", " blood."),
            new Line("I need a ", " liver."),
            new Line("I've heard that ", " blood is tasty..."),
            new Line("", " heart, hmmm..."),
            new Line("I would slay God himself for some ", " flesh."),
            new Line("I could be devouring a ", " whole day."),
            new Line("I've been waiting for too long. Too long or a day to kill a ", "."),
            new Line("", "'s blood shall be spilled"),
            new Line("My curse shall devour ", "'s soul"));
    private static final List<Line> CELESTIAL_LINES = List.of(
            new Line("I love all beings... except ", ", I hate those."),
            new Line("All life must be in balance, what's why I need to kill a ", "."),
            new Line("I am celestial, but I am also a sword. Now get me a ", "."),
            new Line("I'm sorry, but please get me some ", ". No questions."),
            new Line("Celestial sword requires a celestial sacrifice. A ", "."),
            new Line("My next victim should be ", ", just as God intended."),
            new Line("And the next in line is ... ", "!"),
            new Line("The God wants a ", " dead."),
            new Line("For God and honour, go slay a ", "."),
            new Line("Go, get that ", "! For justice!"),
            new Line("The stars have aligned. I can clearly see the ", " that shall die by my blade"));

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
            p.sendMessage(ChatColor.RED + line.getFirstHalf() + toString(id) + line.getSecondHalf());
        } else if (item == Items.CELESTIAL_SWORD) {
            int i = random.nextInt(CELESTIAL_LINES.size());
            Line line = CELESTIAL_LINES.get(i);
            p.sendMessage(ChatColor.YELLOW + line.getFirstHalf() + toString(id) + line.getSecondHalf());
        }
    }

    public static EntityType toEntityType(int id) {
        Validate.isTrue(id <= 61, "Entity ID can't be greater than 61");

        switch (id) {
            case 0:
                return EntityType.BAT;
            case 1:
                return EntityType.BEE;
            case 2:
                return EntityType.BLAZE;
            case 3:
                return EntityType.CAT;
            case 4:
                return EntityType.CHICKEN;
            case 5:
                return EntityType.CAVE_SPIDER;
            case 6:
                return EntityType.COD;
            case 7:
                return EntityType.COW;
            case 8:
                return EntityType.CREEPER;
            case 9:
                return EntityType.DOLPHIN;
            case 10:
                return EntityType.DONKEY;
            case 11:
                return EntityType.DROWNED;
            case 12:
                return EntityType.ENDERMAN;
            case 13:
                return EntityType.FOX;
            case 14:
                return EntityType.GHAST;
            case 15:
                return EntityType.GUARDIAN;
            case 16:
                return EntityType.HOGLIN;
            case 17:
                return EntityType.HUSK;
            case 18:
                return EntityType.HORSE;
            case 19:
                return EntityType.IRON_GOLEM;
            case 20:
                return EntityType.LLAMA;
            case 21:
                return EntityType.MAGMA_CUBE;
            case 22:
                return EntityType.MUSHROOM_COW;
            case 23:
                return EntityType.OCELOT;
            case 24:
                return EntityType.PANDA;
            case 25:
                return EntityType.PARROT;
            case 26:
                return EntityType.PHANTOM;
            case 27:
                return EntityType.PIG;
            case 28:
                return EntityType.PIGLIN;
            case 29:
                return EntityType.PIGLIN_BRUTE;
            case 30:
                return EntityType.PILLAGER;
            case 31:
                return EntityType.POLAR_BEAR;
            case 32:
                return EntityType.PUFFERFISH;
            case 33:
                return EntityType.RABBIT;
            case 34:
                return EntityType.SALMON;
            case 35:
                return EntityType.SHEEP;
            case 36:
                return EntityType.SILVERFISH;
            case 37:
                return EntityType.SKELETON;
            case 38:
                return EntityType.SLIME;
            case 39:
                return EntityType.SNOWMAN;
            case 40:
                return EntityType.SPIDER;
            case 41:
                return EntityType.SQUID;
            case 42:
                return EntityType.STRAY;
            case 43:
                return EntityType.STRIDER;
            case 44:
                return EntityType.TURTLE;
            case 45:
                return EntityType.TROPICAL_FISH;
            case 46:
                return EntityType.WITCH;
            case 47:
                return EntityType.WITHER_SKELETON;
            case 48:
                return EntityType.WOLF;
            case 49:
                return EntityType.ZOGLIN;
            case 50:
                return EntityType.ZOMBIE;
            case 51:
                return EntityType.ZOMBIFIED_PIGLIN;
        }
        return EntityType.FOX;
    }

    public static String toString(int id) {
        Validate.isTrue(id <= 61, "Entity ID can't be greater than 61");

        switch (id) {
            case 0:
                return "蝙蝠";
            case 1:
                return "蜜蜂";
            case 2:
                return "烈焰人";
            case 3:
                return "猫";
            case 4:
                return "鸡";
            case 5:
                return "洞穴蜘蛛";
            case 6:
                return "生鳕鱼";
            case 7:
                return "牛";
            case 8:
                return "苦力怕";
            case 9:
                return "海豚";
            case 10:
                return "驴";
            case 11:
                return "溺尸";
            case 12:
                return "末影人";
            case 13:
                return "狐狸";
            case 14:
                return "恶魂";
            case 15:
                return "守卫者";
            case 16:
                return "疣猪兽";
            case 17:
                return "尸壳";
            case 18:
                return "马";
            case 19:
                return "铁傀儡";
            case 20:
                return "羊驼";
            case 21:
                return "岩浆怪";
            case 22:
                return "哞菇";
            case 23:
                return "豹猫";
            case 24:
                return "熊猫";
            case 25:
                return "鹦鹉";
            case 26:
                return "幻翼";
            case 27:
                return "猪";
            case 28:
                return "猪灵";
            case 29:
                return "猪灵蛮兵";
            case 30:
                return "掠夺者";
            case 31:
                return "北极熊";
            case 32:
                return "河豚";
            case 33:
                return "兔子";
            case 34:
                return "生鲑鱼";
            case 35:
                return "羊";
            case 36:
                return "蠹虫";
            case 37:
                return "骷髅";
            case 38:
                return "史莱姆";
            case 39:
                return "雪人";
            case 40:
                return "蜘蛛";
            case 41:
                return "鱿鱼";
            case 42:
                return "流浪者";
            case 43:
                return "炽足兽";
            case 44:
                return "海龟";
            case 45:
                return "热带鱼";
            case 46:
                return "女巫";
            case 47:
                return "凋零骷髅";
            case 48:
                return "狼";
            case 49:
                return "僵尸疣猪兽";
            case 50:
                return "僵尸";
            case 51:
                return "僵尸猪人";
        }
        return "狐狸";
    }
}

class Line {
    public Line(String firstHalf, String secondHalf) {
		this.firstHalf = firstHalf;
		this.secondHalf = secondHalf;
	}
	public String getSecondHalf() {
		return secondHalf;
	}
	public String getFirstHalf() {
		return firstHalf;
	}
	@Getter
    private final String firstHalf;
    @Getter
    private final String secondHalf;
}
