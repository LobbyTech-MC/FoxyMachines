package me.gallowsdove.foxymachines.tasks;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.utils.QuestUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ThreadLocalRandom;

@EnableAsync
public class QuestTicker implements Runnable{
    @Override
    @Async
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        if (random.nextInt(120) == 66) {
            for (Player p: Bukkit.getOnlinePlayers()) {
                if (SlimefunUtils.containsSimilarItem(p.getInventory(), Items.CURSED_SWORD, false)) {
                    QuestUtils.sendQuestLine(p, Items.CURSED_SWORD);
                } else if (SlimefunUtils.containsSimilarItem(p.getInventory(), Items.CELESTIAL_SWORD, false)) {
                    QuestUtils.sendQuestLine(p, Items.CELESTIAL_SWORD);
                }
            }
        }
    }
}
