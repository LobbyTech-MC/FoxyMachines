package me.gallowsdove.foxymachines.commands;

import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.mooy1.infinitylib.commands.SubCommand;
import me.gallowsdove.foxymachines.abstracts.CustomMob;

public final class SummonCommand extends SubCommand {

    public SummonCommand() {
        super("summon", "召唤自定义生物", "foxymachines.admin");
    }

    @Override
    protected void execute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "使用方法: /foxy summon <MOB_ID>");
            return;
        }

        CustomMob mob = CustomMob.getById(args[0]);

        if (mob != null) {
            mob.spawn(player.getLocation());
        }
    }


    @Override
    protected void complete(@Nonnull CommandSender sender, @Nonnull String[] args, @Nonnull List<String> tabs) {
        tabs.addAll(CustomMob.MOBS.keySet());
    }
}
