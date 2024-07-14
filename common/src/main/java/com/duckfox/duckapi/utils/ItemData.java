package com.duckfox.duckapi.utils;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemData {
    ItemStack itemStack;
    List<Integer> slots;
    List<String> lore;
    String itemName;

    public ItemData() {
    }

    public ItemData(ItemStack itemStack, List<Integer> slots) {
        this.itemStack = itemStack;
        this.slots = slots;
    }

    public ItemData(ItemStack itemStack, List<Integer> slots, List<String> lore, String itemName) {
        this.itemStack = itemStack;
        this.slots = slots;
        this.lore = lore;
        this.itemName = itemName;
    }

    /**
     * 获取
     * @return itemStack
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * 设置
     * @param itemStack
     */
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * 获取
     * @return slots
     */
    public List<Integer> getSlots() {
        return slots;
    }

    /**
     * 设置
     * @param slots
     */
    public void setSlots(List<Integer> slots) {
        this.slots = slots;
    }

    public String toString() {
        return "ItemData{itemStack = " + itemStack + ", slots = " + slots + "}";
    }

    /**
     * 获取
     * @return lore
     */
    public List<String> getLore() {
        return lore;
    }

    /**
     * 设置
     * @param lore
     */
    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    /**
     * 获取
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
