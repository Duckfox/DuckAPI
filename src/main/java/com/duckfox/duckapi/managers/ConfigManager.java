package com.duckfox.duckapi.managers;

import com.duckfox.duckapi.utils.FileUtil;
import com.duckfox.duckapi.utils.StringUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ConfigManager {

    JavaPlugin plugin;
    FileConfiguration configuration;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        init();
    }

    public void init() {
        configuration = FileUtil.loadFileAndReturn(this.plugin, "config.yml");
    }

    public Object get(String key) {
        if (!configuration.contains(key)) {
            return key;
        }
        return configuration.get(key);
    }


    public String getString(String key, String... args) {
        return StringUtil.format((String) get(key), args);
    }

    public String getString(String key, Player player, String... args) {
        return StringUtil.format((String) get(key), player, args);
    }

    public List<String> getStringList(String key, String... args) {
        return StringUtil.format(configuration.getStringList(key), args);
    }

    public List<String> getStringList(String key, Player player, String... args) {
        return StringUtil.format(configuration.getStringList(key), player, args);
    }

    public int getInteger(String key) {
        return configuration.getInt(key);
    }
    public ItemStack getItemStack(String key){
        return configuration.getItemStack(key);
    }
    public boolean getBoolean(String key){
        return configuration.getBoolean(key);
    }
    public ConfigurationSection getSection(String key){
        return configuration.getConfigurationSection(key);
    }

    public float getFloat(String key){
        return ((float) configuration.getDouble(key));
    }
    public double getDouble(String key){
        return configuration.getDouble(key);
    }
    public FileConfiguration getConfig(){
        return configuration;
    }

}
