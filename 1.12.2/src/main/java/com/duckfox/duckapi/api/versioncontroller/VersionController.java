package com.duckfox.duckapi.api.versioncontroller;

import com.duckfox.duckapi.nms.ItemStackProxy;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class VersionController implements IVersionController{
    @Override
    public Object getSpeciesFromDex(int dex) {
        return EnumSpecies.getFromDex(dex);
    }

    @Override
    public int getDexFromSpecies(Object species) {
        return ((EnumSpecies) species).getNationalPokedexInteger();
    }

    @Override
    public String getSpeciesLocalizedName(Object species) {
        return ((EnumSpecies)species).getLocalizedName();
    }

    @Override
    public Pokemon createPokemonFromSpecies(Object species) {
        return Pixelmon.pokemonFactory.create((EnumSpecies) species);
    }

    @Override
    public void setForm(Pokemon pokemon, int form) {
        pokemon.setForm(form);
    }

    @Override
    public boolean hasFlag(Pokemon pokemon, String flag) {
        return pokemon.hasSpecFlag(flag);
    }

    @Override
    public void addFlag(Pokemon pokemon, String flag) {
        pokemon.addSpecFlag(flag);
    }

    @Override
    public boolean isSpeciesLegendary(Object species) {
        return ((EnumSpecies)species).isLegendary();
    }

    @Override
    public boolean isSpeciesUltraBeast(Object species) {
        return ((EnumSpecies)species).isUltraBeast();
    }

    @Override
    public String getSpeciesPokemonName(Object species) {
        return ((EnumSpecies)species).getPokemonName();
    }

    @Override
    public Object getSpeciesFromNameAnyCase(String name) {
        return EnumSpecies.getFromNameAnyCase(name);
    }

    @Override
    public Object getParty(Player player) {
        return Pixelmon.storageManager.getParty(player.getUniqueId());
    }

    @Override
    public Object getParty(UUID uuid) {
        return Pixelmon.storageManager.getParty(uuid);
    }

    @Override
    public void partySet(Object party, int slot, Pokemon pokemon) {
        ((PlayerPartyStorage)party).set(slot,pokemon);
    }

    @Override
    public Pokemon[] partyGetAll(Object party) {
        return ((PlayerPartyStorage)party).getAll();
    }

    @Override
    public Pokemon partyGet(Object party, int slot) {
        return ((PlayerPartyStorage)party).get(slot);
    }

    @Override
    public ItemStack getPhoto(Pokemon pokemon) {
        return ItemStackProxy.asBukkitCopy(ItemPixelmonSprite.getPhoto(pokemon));
    }
}
