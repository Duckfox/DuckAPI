package com.duckfox.duckapi.utils;

import net.minecraft.item.ItemStack;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;

@Deprecated
public class NMSUtil {
    private NMSUtil() {
    }

    public static ItemStack BKTToNMSItemStack(org.bukkit.inventory.ItemStack original) {
        return CraftItemStack.asNMSCopy(original);
    }

    public static org.bukkit.inventory.ItemStack NMSToBKTItemStack(ItemStack original) {
        return CraftItemStack.asBukkitCopy(original);
    }

    public static net.minecraft.entity.Entity BKTToNMSEntity(Entity entity) {
        return ((CraftEntity) entity).getHandle();
    }

    public static Entity NMSToBKTEntity(net.minecraft.entity.Entity entity) {
        return entity.getBukkitEntity();
    }

}
