package com.duckfox.duckapi.utils;

import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Deprecated
@SuppressWarnings("unused")
public class NBTUtil {
    private NBTUtil() {
    }

    public static NBTTagCompound getNBT(ItemStack itemStack) {
        return NMSUtil.BKTToNMSItemStack(itemStack).func_77978_p() == null ? new NBTTagCompound() : NMSUtil.BKTToNMSItemStack(itemStack).func_77978_p();
    }

    public static ItemStack writeNBT(net.minecraft.item.ItemStack itemStack, NBTTagCompound nbt) {
        itemStack.func_77982_d(nbt);
        return NMSUtil.NMSToBKTItemStack(itemStack);
    }

    public static ItemStack writeNBT(ItemStack itemStack, NBTTagCompound nbt) {
        return writeNBT(NMSUtil.BKTToNMSItemStack(itemStack), nbt);
    }

    public static NBTTagCompound copyNBT(ItemStack itemStack) {
        return getNBT(itemStack).func_74737_b();
    }

    public static NBTTagCompound copyNBT(NBTTagCompound original) {
        return original.func_74737_b();
    }

    public static ItemStack writeStringNBT(ItemStack itemStack, String key, String value) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_74778_a(key, value);
        return writeNBT(nms, nbt);

    }


    public static ItemStack writeStringsNBT(ItemStack itemStack, String... strings) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        if (strings == null) {
            return itemStack;
        }
        int num = strings.length / 2;
        int count = 0;
        for (int i = 0; i < num; i++) {
            itemStack = writeStringNBT(itemStack, strings[count++], strings[count++]);
        }
        return writeNBT(nms, nbt);
    }


    public static ItemStack writeIntegerNBT(ItemStack itemStack, String key, int value) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_74768_a(key, value);
        writeNBT(nms, nbt);
        return writeNBT(nms, nbt);
    }

    public static ItemStack writeBooleanNBT(ItemStack itemStack, String key, boolean value) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_74757_a(key, value);
        return writeNBT(nms, nbt);
    }

    public static ItemStack writeByteNBT(ItemStack itemStack, String key, byte value) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_74774_a(key, value);
        return writeNBT(nms, nbt);
    }

    public static ItemStack writeUUIDNBT(ItemStack itemStack, String key, UUID value) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_186854_a(key, value);
        return writeNBT(nms, nbt);
    }

    public static ItemStack writeByteArrayNBT(ItemStack itemStack, String key, byte[] value) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_74773_a(key, value);
        return writeNBT(nms, nbt);
    }

    public static ItemStack writeIntArrayNBT(ItemStack itemStack, String key, int[] value) {
        net.minecraft.item.ItemStack nms = NMSUtil.BKTToNMSItemStack(itemStack);
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_74783_a(key, value);
        return writeNBT(nms, nbt);
    }

    public static String readStringNBT(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_74779_i(key);
    }

    public static int readIntegerNBT(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_74762_e(key);
    }

    public static byte readByteNBT(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_74771_c(key);
    }

    public static boolean readBooleanNBT(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_74767_n(key);
    }

    public static UUID readUUIDNBT(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_186857_a(key);
    }

    public static byte[] readByteArrayNBT(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_74770_j(key);
    }

    public static int[] readIntArrayNBT(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_74759_k(key);
    }


    public static boolean hasKey(ItemStack itemStack, String key) {
        return getNBT(itemStack).func_74764_b(key);
    }

    public static ItemStack removeTag(ItemStack itemStack, String key) {
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.func_82580_o(key);
        return writeNBT(itemStack, nbt);
    }
}
