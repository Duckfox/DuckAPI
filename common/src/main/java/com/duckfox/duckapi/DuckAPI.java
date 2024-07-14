package com.duckfox.duckapi;

import com.duckfox.duckapi.api.versioncontroller.IVersionController;
import com.duckfox.duckapi.managers.MessageManager;
import com.duckfox.duckapi.nms.Version;
import org.bukkit.Bukkit;

public class DuckAPI extends DuckPlugin {
    public static DuckAPI instance;
    public static boolean isPapiLoad = false;
    public static boolean isPixelmonLoad = false;
    public static MessageManager messageManager;
    private static IVersionController versionController;

    public static IVersionController getVersionController() {
        return versionController;
    }

    @Override
    public void onEnable() {
        instance = this;
        if (isPixelmonLoad()) {
            isPixelmonLoad = true;
            getLogger().info("§3已成功检测到§bPixelmon§3Mod!");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            isPapiLoad = true;
            getLogger().info("成功检测到PlaceholderAPI插件！");
        }
        try {
            Class<?> aClass = Class.forName("com.duckfox.duckapi.api.versioncontroller.VersionController");
            if (IVersionController.class.isAssignableFrom(aClass)){
                versionController = (IVersionController) aClass.newInstance();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            throw new RuntimeException(e);
        }
        if (Bukkit.getVersion().contains("1.12.2")) {
            getServer().getPluginCommand("DuckAPI").setExecutor(new Commands());
        }
        getLogger().info("§b服务器版本:" + Bukkit.getVersion());

        getLogger().info(" §3-----§bDuckAPI§3-----");
        getLogger().info("    §3作者:§bDuck_fox");
        getLogger().info("    §3QQ:§b2660759310");
        getLogger().info(" §3---------------------");
        getLogger().info("§3DuckAPI§b插件已成功加载！");
        messageManager = new MessageManager(instance, "messages.yml", "[DuckAPI]");
    }

    @Override
    public void onDisable() {
        getLogger().info("§3DuckAPI§b插件已成功卸载！");
    }


    private boolean isPixelmonLoad() {
        try {
            Class.forName("com.pixelmonmod.pixelmon.Pixelmon");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
