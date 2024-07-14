package com.duckfox.duckapi.api.versioncontroller;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@SuppressWarnings("unused")
public interface IVersionController {
    Object getSpeciesFromDex(int dex);
    int getDexFromSpecies(Object species);
    String getSpeciesLocalizedName(Object species);

    Pokemon createPokemonFromSpecies(Object species);
    void setForm(Pokemon pokemon,int form);
    boolean hasFlag(Pokemon pokemon,String flag);
    void addFlag(Pokemon pokemon,String flag);
    boolean isSpeciesLegendary(Object species);
    boolean isSpeciesUltraBeast(Object species);
    String getSpeciesPokemonName(Object species);
    Object getSpeciesFromNameAnyCase(String name);
    Object getParty(Player player);
    Object getParty(UUID uuid);
    void partySet(Object party,int slot,Pokemon pokemon);
    Pokemon[] partyGetAll(Object party);
    Pokemon partyGet(Object party,int slot);
    ItemStack getPhoto(Pokemon pokemon);
}
