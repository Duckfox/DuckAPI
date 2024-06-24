package com.duckfox.duckapi.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {
    private FileUtil() {
    }

    public static FileConfiguration getConfig(JavaPlugin plugin, String fileName) {
        return YamlConfiguration.loadConfiguration(getFile(plugin, fileName));
    }

    public static File getFile(JavaPlugin plugin, String fileName) {
        return new File(plugin.getDataFolder(), fileName);
    }

    public static FileConfiguration loadFileAndReturn(JavaPlugin plugin, String fileName) {
        return YamlConfiguration.loadConfiguration(saveFile(plugin, fileName));
    }

    public static File saveFile(JavaPlugin plugin, String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        return file;
    }
    public static void mkDir(JavaPlugin plugin,String path) {
        File file = getFile(plugin, path);
        if (!file.exists()) {
            try {
                Files.createDirectories(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
