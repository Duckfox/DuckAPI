package com.duckfox.duckapi.nms;

import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
import java.util.UUID;

@SuppressWarnings("unused")
public class ItemStackProxy {
    public static Class<?> craftItemStack;
    public static Class<?> itemStack;
    public static Method asNMSCopy;
    public static Method asBukkitCopy;
    public static Method getNBT;
    public static Method setNBT;

    static {
        if (!Version.getVersion().isVersion(Version.UNKNOWN)) {
            try {
                craftItemStack = Class.forName("org.bukkit.craftbukkit." + Version.getVersion().nmsName + ".inventory.CraftItemStack");
                if (Version.getVersion().isVersion(Version.V1_12_2)) {
                    itemStack = Class.forName("net.minecraft.item.ItemStack");
                    getNBT = itemStack.getDeclaredMethod("func_77978_p");
                    setNBT = itemStack.getDeclaredMethod("func_77982_d", NBTProxy.nbtClass);
                } else {
                    itemStack = Class.forName("net.minecraft.server." + Version.getVersion().nmsName + ".ItemStack");
                    getNBT = itemStack.getDeclaredMethod("getOrCreateTag");
                    setNBT = itemStack.getDeclaredMethod("setTag", NBTProxy.nbtClass);
                }
                asNMSCopy = craftItemStack.getDeclaredMethod("asNMSCopy", ItemStack.class);
                asBukkitCopy = craftItemStack.getDeclaredMethod("asBukkitCopy", itemStack);
                asNMSCopy.setAccessible(true);
                asBukkitCopy.setAccessible(true);
                getNBT.setAccessible(true);
                setNBT.setAccessible(true);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SneakyThrows
    public static Object asNMSCopy(org.bukkit.inventory.ItemStack bukkit) {
        return asNMSCopy.invoke(null, bukkit.clone());
    }

    @SneakyThrows
    public static ItemStack asBukkitCopy(Object nms) {
        return (ItemStack) asBukkitCopy.invoke(null, nms);
    }

    @SneakyThrows
    public static Object getNBT(ItemStack itemStack) {
        Object nbt = getNBT.invoke(asNMSCopy(itemStack));
        return nbt == null ? NBTProxy.nbtClass.newInstance() : nbt;
    }

    @SneakyThrows
    public static Object setNBT(ItemStack itemStack, Object nbt) {
        Object nmsCopy = asNMSCopy(itemStack);
        setNBT.invoke(nmsCopy, nbt);
        return nmsCopy;
    }

    @SneakyThrows
    public static ItemStack writeStringNBT(ItemStack itemStack, String key, String value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setString(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeIntegerNBT(ItemStack itemStack, String key, int value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setInteger(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeBooleanNBT(ItemStack itemStack, String key, boolean value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setBoolean(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeByteNBT(ItemStack itemStack, String key, byte value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setByte(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeDoubleNBT(ItemStack itemStack, String key, double value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setDouble(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeFloatNBT(ItemStack itemStack, String key, float value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setFloat(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeLongNBT(ItemStack itemStack, String key, long value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setLong(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeShortNBT(ItemStack itemStack, String key, short value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setShort(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    @SneakyThrows
    public static ItemStack writeUniqueIdNBT(ItemStack itemStack, String key, UUID value) {
        Object nbt = getNBT(itemStack);
        NBTProxy.setUniqueId(nbt, key, value);
        return asBukkitCopy(setNBT(itemStack, nbt));
    }

    public static String readStringNBT(ItemStack itemStack, String key) {
        return NBTProxy.getString(getNBT(itemStack), key);
    }

    public static float readFloatNBT(ItemStack itemStack, String key) {
        return NBTProxy.getFloat(getNBT(itemStack), key);
    }

    public static double readDoubleNBT(ItemStack itemStack, String key) {
        return NBTProxy.getDouble(getNBT(itemStack), key);
    }

    public static int readIntegerNBT(ItemStack itemStack, String key) {
        return NBTProxy.getInteger(getNBT(itemStack), key);
    }

    public static long readLongNBT(ItemStack itemStack, String key) {
        return NBTProxy.getLong(getNBT(itemStack), key);
    }

    public static short readShortNBT(ItemStack itemStack, String key) {
        return NBTProxy.getShort(getNBT(itemStack), key);
    }

    public static byte readByteNBT(ItemStack itemStack, String key) {
        return NBTProxy.getByte(getNBT(itemStack), key);
    }

    public static UUID readUniqueIdNBT(ItemStack itemStack, String key) {
        return NBTProxy.getUniqueId(getNBT(itemStack), key);
    }

    public static boolean readBooleanNBT(ItemStack itemStack, String key) {
        return NBTProxy.getBoolean(getNBT(itemStack), key);
    }


}
