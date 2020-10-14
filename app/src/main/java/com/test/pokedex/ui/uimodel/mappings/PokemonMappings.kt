package com.test.pokedex.ui.uimodel.mappings

import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.ui.uimodel.states.PokemonState
import java.util.*

/**
 * Maps a Pokemon to a UIState that can be used with Uniflow
 */
fun Pokemon.mapToPokemonState(): PokemonState {
    return PokemonState(
        name,
        listOf(
            sprites?.other?.officialArtwork?.frontDefault ?: "",
            sprites?.other?.dreamWorld?.frontDefault ?: ""
        ).filter { it.isNotEmpty() },
        types?.map { it.type.name.capitalize(Locale.getDefault()) } ?: emptyList(),
        stats?.map {
            Pair(
                it.stat.name.replace('-', ' ').capitalize(Locale.getDefault()),
                it.baseStat
            )
        } ?: emptyList()
    )
}