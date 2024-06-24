package com.duckfox.duckapi;

import com.duckfox.duckapi.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckAPI extends JavaPlugin {
    public static DuckAPI instance;
    public static boolean isPapiLoad = false;
    public static boolean isPixelmonLoad = false;
    public static MessageManager messageManager;

    @Override
    public void onEnable() {
        instance = this;
        if (isPixelmonLoad()) {
            isPixelmonLoad = true;
            getLogger().info("§3已成功检测到§bPixelmon§3Mod!");
        } else {
            getLogger().severe("§c未检测到Pixelmon§4Mod!");
//            getServer().getPluginManager().disablePlugin(this);
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().warning("检测到您未安装PlaceholderAPI插件，建议您安装获取最佳体验！");
        }else {
            isPapiLoad = true;
        }
        if (Bukkit.getVersion().contains("1.12.2")){
            getServer().getPluginCommand("DuckAPI").setExecutor(new Commands());
        }
        getLogger().info("§b服务器版本:"+Bukkit.getVersion());
        System.out.println("jajkakj");
        // Plugin startup logic
        getLogger().info(" §3-----§bDuckAPI§3-----");
        getLogger().info("    §3作者:§bDuck_fox");
        getLogger().info("    §3QQ:§b2660759310");
        getLogger().info(" §3---------------------");
        getLogger().info("§3DuckAPI§b插件已成功加载！");
        messageManager= new MessageManager(instance,"message.yml","[DuckAPI]");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
