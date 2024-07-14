package com.duckfox.duckapi.utils;

import com.pixelmonmod.pixelmon.items.ItemUIElement;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Deprecated
@SuppressWarnings("unused")
public class InventoryUtil {
    private InventoryUtil() {
    }

    public static boolean isValuableSize(int size) {
        return size % 9 == 0 && size > 0 && size <= 54;
    }

    public static ItemData makeItem(JavaPlugin plugin, String configName, ConfigurationSection itemSection, String key) {
        if (itemSection == null || key == null) {
            return null;
        }
        if (!itemSection.contains(key)) {
            return null;
        }
        Material material = Material.matchMaterial(itemSection.getString(key + ".material"));
        List<Integer> slotList = itemSection.getIntegerList(key + ".slots");
        if (material != null && slotList != null) {
            List<String> commands = StringUtil.format(itemSection.getStringList(key + ".commands"));
            ItemStack itemStack = new ItemStack(
                    material,
                    itemSection.getInt(key + ".amount") == 0 ? 1 : itemSection.getInt(key + ".amount"),
                    (short) itemSection.getInt(key + ".damage"),
                    (byte) itemSection.getInt(key + ".data"));
            ItemMeta itemMeta = itemStack.getItemMeta();
            String name = StringUtil.format(itemSection.getString(key + ".name"));
            List<String> lore = StringUtil.format(itemSection.getStringList(key + ".lore"));
            if (name != null) itemMeta.setDisplayName(name);
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            if (!commands.isEmpty()) {
                itemStack = NBTUtil.writeStringsNBT(itemStack,
                        "plugin", plugin.getName(),
                        "configName", configName,
                        "commandKey", key);
            }
            return new ItemData(itemStack, slotList,lore,name);

        }
        return null;
    }

    public static ItemStack getTextInfoWithImage(String image, Integer sizeWeight, Integer sizeHeight, Integer colorR, Integer colorG, Integer colorB, Integer colorA, String text, Double TextureSizeWeight, Double TextureSizeHeight, Double TextScale, Integer PosOffsetX, Integer PosOffsetY) {
        net.minecraft.item.ItemStack itemStack = ItemUIElement.builder().setImage(image).setSize(sizeWeight, sizeHeight).setColor(colorR, colorG, colorB, colorA).setText(text).setTextureSize(Float.valueOf(String.valueOf(TextureSizeWeight)), Float.valueOf(String.valueOf(TextureSizeHeight))).setTextScale(Float.valueOf(String.valueOf(TextScale))).setPosOffset(PosOffsetX, PosOffsetY).build();
        ItemStack pokeItem = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta itemMeta = pokeItem.getItemMeta();
        itemMeta.setDisplayName(text);
        pokeItem.setItemMeta(itemMeta);
        return pokeItem;
    }
}
