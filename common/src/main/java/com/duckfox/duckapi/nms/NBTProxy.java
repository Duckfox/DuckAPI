package com.duckfox.duckapi.nms;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.UUID;

public class NBTProxy {
    public static Class<?> nbtClass;
    public static Method setString;
    public static Method setFloat;
    public static Method setDouble;
    public static Method setInteger;
    public static Method setLong;
    public static Method setShort;
    public static Method setBoolean;
    public static Method setByte;
    public static Method setUniqueId;
    public static Method getString;
    public static Method getFloat;
    public static Method getDouble;
    public static Method getInteger;
    public static Method getLong;
    public static Method getShort;
    public static Method getBoolean;
    public static Method getByte;
    public static Method getUniqueId;
    public static Method hasKey;


    static {
        try {
            if (Version.getVersion().isVersion(Version.V1_12_2)) {
                nbtClass = Class.forName("net.minecraft.nbt.NBTTagCompound");
                setString = nbtClass.getDeclaredMethod("func_74778_a", String.class, String.class);
                setFloat = nbtClass.getDeclaredMethod("func_74776_a", String.class, float.class);
                setDouble = nbtClass.getDeclaredMethod("func_74780_a", String.class, double.class);
                setInteger = nbtClass.getDeclaredMethod("func_74768_a", String.class, int.class);
                setLong = nbtClass.getDeclaredMethod("func_74772_a", String.class, long.class);
                setUniqueId = nbtClass.getDeclaredMethod("func_186854_a", String.class, UUID.class);
                setBoolean = nbtClass.getDeclaredMethod("func_74757_a", String.class, boolean.class);
                setShort = nbtClass.getDeclaredMethod("func_74777_a", String.class, short.class);
                setByte = nbtClass.getDeclaredMethod("func_74774_a", String.class, byte.class);
                getString = nbtClass.getDeclaredMethod("func_74779_i", String.class);
                getFloat = nbtClass.getDeclaredMethod("func_74760_g", String.class);
                getDouble = nbtClass.getDeclaredMethod("func_74769_h", String.class);
                getInteger = nbtClass.getDeclaredMethod("func_74762_e", String.class);
                getLong = nbtClass.getDeclaredMethod("func_74763_f", String.class);
                getShort = nbtClass.getDeclaredMethod("func_74765_d", String.class);
                getByte = nbtClass.getDeclaredMethod("func_74771_c", String.class);
                getUniqueId = nbtClass.getDeclaredMethod("func_186857_a", String.class);
                getBoolean = nbtClass.getDeclaredMethod("func_74767_n", String.class);
                hasKey = nbtClass.getDeclaredMethod("func_74764_b", String.class);
            } else {
                nbtClass = Class.forName("net.minecraft.server." + Version.getVersion().nmsName + ".NBTTagCompound");
                setString = nbtClass.getDeclaredMethod("setString", String.class, String.class);
                setFloat = nbtClass.getDeclaredMethod("setFloat", String.class, float.class);
                setDouble = nbtClass.getDeclaredMethod("setDouble", String.class, double.class);
                setInteger = nbtClass.getDeclaredMethod("setInt", String.class, int.class);
                setLong = nbtClass.getDeclaredMethod("setLong", String.class, long.class);
                setUniqueId = nbtClass.getDeclaredMethod("a", String.class, UUID.class);
                setBoolean = nbtClass.getDeclaredMethod("setBoolean", String.class, boolean.class);
                setShort = nbtClass.getDeclaredMethod("setShort", String.class, short.class);
                setByte = nbtClass.getDeclaredMethod("setByte", String.class, byte.class);
                getString = nbtClass.getDeclaredMethod("getString", String.class);
                getFloat = nbtClass.getDeclaredMethod("getFloat", String.class);
                getDouble = nbtClass.getDeclaredMethod("getDouble", String.class);
                getInteger = nbtClass.getDeclaredMethod("getInt", String.class);
                getLong = nbtClass.getDeclaredMethod("getLong", String.class);
                getShort = nbtClass.getDeclaredMethod("getShort", String.class);
                getByte = nbtClass.getDeclaredMethod("getByte", String.class);
                getUniqueId = nbtClass.getDeclaredMethod("a", String.class);
                getBoolean = nbtClass.getDeclaredMethod("getBoolean", String.class);
                hasKey = nbtClass.getDeclaredMethod("hasKey", String.class);
            }
            setString.setAccessible(true);
            setFloat.setAccessible(true);
            setDouble.setAccessible(true);
            setInteger.setAccessible(true);
            setLong.setAccessible(true);
            setShort.setAccessible(true);
            setByte.setAccessible(true);
            setUniqueId.setAccessible(true);
            setBoolean.setAccessible(true);
            getString.setAccessible(true);
            getFloat.setAccessible(true);
            getDouble.setAccessible(true);
            getInteger.setAccessible(true);
            getLong.setAccessible(true);
            getShort.setAccessible(true);
            getByte.setAccessible(true);
            getUniqueId.setAccessible(true);
            getBoolean.setAccessible(true);
            hasKey.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static boolean hasKey(Object nbt, String key) {
        return (boolean) hasKey.invoke(nbt, key);
    }

    @SneakyThrows
    public static void setString(Object nbt, String key, String value) {
        setString.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setFloat(Object nbt, String key, float value) {
        setFloat.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setDouble(Object nbt, String key, double value) {
        setDouble.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setBoolean(Object nbt, String key, boolean value) {
        setBoolean.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setShort(Object nbt, String key, short value) {
        setShort.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setByte(Object nbt, String key, byte value) {
        setByte.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setInteger(Object nbt, String key, int value) {
        setInteger.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setLong(Object nbt, String key, long value) {
        setLong.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static void setUniqueId(Object nbt, String key, UUID value) {
        setUniqueId.invoke(nbt, key, value);
    }

    @SneakyThrows
    public static String getString(Object nbt, String key) {
        return (String) getString.invoke(nbt, key);
    }

    @SneakyThrows
    public static float getFloat(Object nbt, String key) {
        return (Float) getFloat.invoke(nbt, key);
    }

    @SneakyThrows
    public static double getDouble(Object nbt, String key) {
        return (Double) getDouble.invoke(nbt, key);
    }

    @SneakyThrows
    public static int getInteger(Object nbt, String key) {
        return (Integer) getInteger.invoke(nbt, key);
    }

    @SneakyThrows
    public static long getLong(Object nbt, String key) {
        return (Long) getLong.invoke(nbt, key);
    }

    @SneakyThrows
    public static short getShort(Object nbt, String key) {
        return (Short) getShort.invoke(nbt, key);
    }

    @SneakyThrows
    public static byte getByte(Object nbt, String key) {
        return (Byte) getByte.invoke(nbt, key);
    }

    @SneakyThrows
    public static UUID getUniqueId(Object nbt, String key) {
        return (UUID) getUniqueId.invoke(nbt, key);
    }

    @SneakyThrows
    public static boolean getBoolean(Object nbt, String key) {
        return (Boolean) getBoolean.invoke(nbt, key);
    }


}
