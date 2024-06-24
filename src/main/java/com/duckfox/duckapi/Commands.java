package com.duckfox.duckapi;

import com.duckfox.duckapi.utils.ComponentUtil;
import com.duckfox.duckapi.utils.NBTUtil;
import com.duckfox.duckapi.utils.StringUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player && commandSender.isOp()) {
            Player player = (Player) commandSender;
            if (args.length >= 1) {
                if ("itemNBT".equalsIgnoreCase(args[0])) {
                    ItemStack itemStack = player.getInventory().getItemInMainHand();
                    if (itemStack == null || itemStack.getType() == Material.AIR) {
                        return false;
                    }
                    player.sendMessage("以下是" + itemStack.getItemMeta().getDisplayName() + "的NBT数据:");
                    player.sendMessage(NBTUtil.getNBT(itemStack).toString());
                }
            }
        }
        return false;
    }

}
