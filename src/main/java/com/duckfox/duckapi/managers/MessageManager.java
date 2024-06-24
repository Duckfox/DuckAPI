package com.duckfox.duckapi.managers;

import com.duckfox.duckapi.utils.FileUtil;
import com.duckfox.duckapi.utils.ReflectionUtil;
import com.duckfox.duckapi.utils.StringUtil;
import com.google.common.collect.Lists;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    JavaPlugin plugin;
    FileConfiguration messageConfig;
    String prefix;
    String filePath;

    public MessageManager(JavaPlugin plugin, String filePath, String prefix) {
        this.plugin = plugin;
        this.prefix = prefix;
        init(filePath);
    }

    public void init(String filePath) {
        this.filePath = filePath;
        messageConfig = FileUtil.loadFileAndReturn(this.plugin, filePath);
    }

    public Object get(String key) {
        if (!messageConfig.contains(key)) {
            return key;
        }
        return messageConfig.get(key);
    }

    public String getString(String key, String... args) {
        return getString(key, true, args);
    }

    public String getString(String key, boolean hasPrefix, String... args) {
        StringBuilder sb = new StringBuilder();
        if (hasPrefix) {
            sb.append(prefix).append(" ");
        }
        sb.append(get(key));
        return StringUtil.format(sb.toString(), args);
    }

    public List<String> getStringList(String key, String... args) {
        List<String> list = new ArrayList<>();
        if (messageConfig.contains(key)) {
            List<String> stringList = messageConfig.getStringList(key);
            list = StringUtil.format(stringList, args);
        }
        return list;
    }

    public List<String> getStringList(String key, Player player, String... args) {
        return StringUtil.format(getStringList(key, args), player);
    }

    public void sendMessages(CommandSender target, String key, String... args) {
        List<String> list = getStringList(key, args);
        for (String s : list) {
            target.sendMessage(s);
        }
    }

    public void sendMessages(CommandSender target, String key, Player player, String... args) {
        List<String> list = getStringList(key, player, args);
        for (String s : list) {
            target.sendMessage(s);
        }
    }

    public void sendMessages(CommandSender target, List<String> list, String... args) {
        for (String s : list) {
            target.sendMessage(StringUtil.format(s, args));
        }
    }

    public void sendMessages(CommandSender target, List<String> list, Player player, String... args) {
        for (String s : list) {
            target.sendMessage(StringUtil.format(s, player, args));
        }
    }

    public String getString(String key, Player player, boolean hasPrefix, String... args) {
        return StringUtil.format(getString(key, hasPrefix, args), player);
    }

    public String getString(String key, Player player, String... args) {
        return getString(key, player, true, args);
    }

    public void sendMessage(CommandSender target, String key, boolean hasPrefix, String... args) {
        target.sendMessage(getString(key, hasPrefix, args));
    }

    public void sendMessage(CommandSender target, String key, String... args) {
        target.sendMessage(getString(key, args));
    }

    public void sendMessage(Player target, String key, boolean hasPrefix, String... args) {
        target.sendMessage(getString(key, target, hasPrefix, args));
    }

    public void sendMessage(Player target, Player papiPlayer, String key, boolean hasPrefix, String... args) {
        target.sendMessage(getString(key, papiPlayer, hasPrefix, args));
    }

    public void sendMessage(Player target, Player papiPlayer, String key, String... args) {
        sendMessage(target, papiPlayer, key, true, args);
    }

    public void sendMessage(Player target, String key, String... args) {
        sendMessage(target, key, true, args);
    }

    public void sendMessage(Player target, BaseComponent component, String... args) {
        BaseComponent baseComponent = new TextComponent(this.prefix);
        baseComponent.addExtra(formatComponent(component, target, args));
        target.spigot().sendMessage(baseComponent);
    }

    public void sendToAll(String key, boolean hasPrefix, String... args) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendMessage(player, key, hasPrefix, args);
        }
    }

    public void sendToAll(String key, Player papiPlayer, boolean hasPrefix, String... args) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendMessage(player, papiPlayer, key, hasPrefix, args);
        }
    }

    public void sendToAll(String key, String... args) {
        sendToAll(key, true, args);
    }

    public void sendToAll(String key, Player papiPlayer, String... args) {
        sendToAll(key, papiPlayer, true, args);
    }

    public BaseComponent formatComponent(BaseComponent component, Player player, String... args) {
        // new一个新的component，防止修改原component
        BaseComponent result = new TextComponent();
        // 如果component有extra
        if (component.getExtra() != null && !component.getExtra().isEmpty()) {
            // 复制一个原component，并把extra置空
            BaseComponent duplicate = component.duplicate();
            List<BaseComponent> extra = component.getExtra();
            duplicate.setExtra(new ArrayList<>());
            // 这样就获得了Component本体，将此Component格式化后添加到新的Component里
            BaseComponent formatted = formatComponent(duplicate, player, args);
            // 将原Component里的extra遍历，并挨个格式化，添加到格式化后的Component
            for (BaseComponent baseComponent : extra) {
                formatted.addExtra(formatComponent(baseComponent, player, args));
            }
        }
        // 格式化
        if (component instanceof TextComponent) {
            TextComponent text = (TextComponent) component;
            text.setText(StringUtil.format(text.getText(), player, args));
            if (text.getHoverEvent() != null) {
                text.setHoverEvent(formatHoverEvent(text.getHoverEvent(), player, args));
            }
            if (text.getClickEvent() != null) {
                formatClickEvent(text.getClickEvent(), player, args);
            }
            return text;
        }
        return result;
    }

    private HoverEvent formatHoverEvent(HoverEvent hoverEvent, Player player, String... args) {
        for (int i = 0; i < hoverEvent.getValue().length; i++) {
            hoverEvent.getValue()[i] = formatComponent(hoverEvent.getValue()[i], player, args);
        }
        return hoverEvent;
    }

    private ClickEvent formatClickEvent(ClickEvent clickEvent, Player player, String... args) {
        ReflectionUtil.setFieldValue(clickEvent, "value", StringUtil.format(clickEvent.getValue(), player, args));
        return clickEvent;
    }
}
