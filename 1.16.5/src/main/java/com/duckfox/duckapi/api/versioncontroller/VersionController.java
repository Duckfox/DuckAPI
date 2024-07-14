package com.duckfox.duckapi.api.versioncontroller;

import com.duckfox.duckapi.nms.ItemStackProxy;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.pokemon.species.Stats;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.UUID;

public class VersionController implements IVersionController {
    @Override
    public Object getSpeciesFromDex(int dex) {
        Optional<Species> species = PixelmonSpecies.fromDex(dex);

        return species.orElse(null);
    }

    @Override
    public int getDexFromSpecies(Object species) {
        return ((Species) species).getDex();
    }

    @Override
    public String getSpeciesLocalizedName(Object species) {
        return ((Species) species).getLocalizedName();
    }

    @Override
    public Pokemon createPokemonFromSpecies(Object species) {
        return PokemonFactory.create((Species) species);
    }

    @Override
    public void setForm(Pokemon pokemon, int form) {
        Stats stats = pokemon.getSpecies().getForms().get(form);
        pokemon.setForm(stats);
    }

    @Override
    public boolean hasFlag(Pokemon pokemon, String flag) {
        return pokemon.hasFlag(flag);
    }

    @Override
    public void addFlag(Pokemon pokemon, String flag) {
        pokemon.addFlag(flag);
    }

    @Override
    public boolean isSpeciesLegendary(Object species) {
        return ((Species) species).isLegendary();
    }

    @Override
    public boolean isSpeciesUltraBeast(Object species) {
        return ((Species) species).isLegendary();
    }

    @Override
    public String getSpeciesPokemonName(Object species) {
        return ((Species) species).getName();
    }

    @Override
    public Object getSpeciesFromNameAnyCase(String name) {
        if (name.matches("\\w")) {
            return PixelmonSpecies.fromName(name);
        }
        for (Species species : PixelmonSpecies.getAll()) {
            if (species.getName().equalsIgnoreCase(name) || PokemonBuilder.builder().species(species).build().getLocalizedName().equalsIgnoreCase(name)) {
                return species;
            }
        }
        return null;
    }

    @Override
    public Object getParty(Player player) {
        return StorageProxy.getParty(player.getUniqueId());
    }

    @Override
    public Object getParty(UUID uuid) {
        return StorageProxy.getParty(uuid);
    }

    @Override
    public void partySet(Object party, int slot, Pokemon pokemon) {
        ((PlayerPartyStorage) party).set(slot, pokemon);
    }


    @Override
    public Pokemon[] partyGetAll(Object party) {
        return ((PlayerPartyStorage) party).getAll();
    }

    @Override
    public Pokemon partyGet(Object party, int slot) {
        return ((PlayerPartyStorage) party).get(slot);
    }

    @Override
    public ItemStack getPhoto(Pokemon pokemon) {
        return ItemStackProxy.asBukkitCopy(SpriteItemHelper.getPhoto(pokemon));
    }
}
