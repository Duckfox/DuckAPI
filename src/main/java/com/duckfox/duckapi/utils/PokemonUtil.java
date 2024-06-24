package com.duckfox.duckapi.utils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PokemonUtil {
    private PokemonUtil(){
    }

    public static PlayerPartyStorage getParty(Player player){
        return Pixelmon.storageManager.getParty(player.getUniqueId());
    }
    public static PlayerPartyStorage getParty(UUID uuid){
        return Pixelmon.storageManager.getParty(uuid);
    }

    public static ItemStack getPhoto(Pokemon pokemon){
        return CraftItemStack.asBukkitCopy(ItemPixelmonSprite.getPhoto(pokemon));
    }

    public static List<EnumSpecies> getSpeciesFromStringList(List<String> list){
        List<EnumSpecies> speciesList = new ArrayList<>();
        for (String s : list) {
            EnumSpecies enumSpecies = EnumSpecies.getFromNameAnyCase(s);
            speciesList.add(enumSpecies);
        }
        return speciesList;
    }
}
