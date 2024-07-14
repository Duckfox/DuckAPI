package com.duckfox.duckapi;

import com.duckfox.duckapi.managers.ConfigManager;
import com.duckfox.duckapi.managers.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class DuckPlugin extends JavaPlugin {
    public MessageManager messageManager;
    public ConfigManager configManager;
    public String pluginName;
    public String version;
    public DuckPlugin(){
        this.configManager = new ConfigManager(this);
        this.messageManager = new MessageManager(this,"messages.yml",configManager.getString("prefix"));
    }
    public void reload(){
        this.configManager.init();
        this.messageManager.init("messages.yml");
    }
}
