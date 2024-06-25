package com.duckfox.duckapi.utils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import com.pixelmonmod.pixelmon.items.ItemUIElement;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PokemonUtil {
    private PokemonUtil() {
    }

    public static PlayerPartyStorage getParty(Player player) {
        return Pixelmon.storageManager.getParty(player.getUniqueId());
    }

    public static PlayerPartyStorage getParty(UUID uuid) {
        return Pixelmon.storageManager.getParty(uuid);
    }

    public static ItemStack getPhoto(Pokemon pokemon) {
        return CraftItemStack.asBukkitCopy(ItemPixelmonSprite.getPhoto(pokemon));
    }

    public static List<EnumSpecies> getSpeciesFromStringList(List<String> list) {
        List<EnumSpecies> speciesList = new ArrayList<>();
        for (String s : list) {
            EnumSpecies enumSpecies = EnumSpecies.getFromNameAnyCase(s);
            speciesList.add(enumSpecies);
        }
        return speciesList;
    }

    public static ItemStack getImageItem(String image, int sizeWeight, int sizeHeight, int colorR, int colorG, int colorB, int colorA, String text, double TextureSizeWeight, double TextureSizeHeight, double TextScale, int PosOffsetX, int PosOffsetY) {
        net.minecraft.item.ItemStack itemStack = ItemUIElement.builder().setImage(image).setSize(sizeWeight, sizeHeight).setColor(colorR, colorG, colorB, colorA).setText(text).setTextureSize(Float.valueOf(String.valueOf(TextureSizeWeight)).floatValue(), Float.valueOf(String.valueOf(TextureSizeHeight)).floatValue()).setTextScale(Float.valueOf(String.valueOf(TextScale)).floatValue()).setPosOffset(PosOffsetX, PosOffsetY).build();
        ItemStack pokeItem = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta itemMeta = pokeItem.getItemMeta();
        itemMeta.setDisplayName(text);
        pokeItem.setItemMeta(itemMeta);
        return pokeItem;
    }

    public static ItemStack getTextInfo(int sizeWeight, int sizeHeight, int colorR, int colorG, int colorB, int colorA, String text, double TextureSizeWeight, double TextureSizeHeight, double TextScale, int PosOffsetX, int PosOffsetY) {
        net.minecraft.item.ItemStack itemStack = ItemUIElement.builder().setSize(sizeWeight, sizeHeight).setColor(colorR, colorG, colorB, colorA).setText(text).setTextureSize(Float.valueOf(String.valueOf(TextureSizeWeight)).floatValue(), Float.valueOf(String.valueOf(TextureSizeHeight)).floatValue()).setTextScale(Float.valueOf(String.valueOf(TextScale)).floatValue()).setPosOffset(PosOffsetX, PosOffsetY).build();
        ItemStack pokeItem = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta itemMeta = pokeItem.getItemMeta();
        itemMeta.setDisplayName(text);
        pokeItem.setItemMeta(itemMeta);
        return pokeItem;
    }
}
