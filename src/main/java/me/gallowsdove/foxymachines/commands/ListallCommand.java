package me.gallowsdove.foxymachines.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import me.gallowsdove.foxymachines.abstracts.CustomMob;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class ListallCommand extends SubCommand {
    public ListallCommand() {
        super("listall", "列出所有 FoxyMachines 生成的生物", "foxymachines.admin");
    }

    @Override
    protected void execute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (args.length != 0) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "用法: /foxy listall");
            return;
        }

        CustomMob.debug();
    }

    @Override
    protected void complete(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) { }
}
