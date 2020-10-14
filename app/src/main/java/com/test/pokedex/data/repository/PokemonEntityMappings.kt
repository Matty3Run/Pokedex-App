package com.test.pokedex.data.repository

import com.test.pokedex.data.datasource.database.PokemonEntity
import com.test.pokedex.domain.model.*

fun PokemonEntity.mapToPokemon() =
    Pokemon(
        name,
        Sprites(
            ImagesSources(
                dreamWorld = Image(dreamWorldImage),
                officialArtwork = Image(officialArtworkImage)
            )
        ),
        types?.map { TypeSlot(1, Type(it)) },
        stats
    )

fun List<PokemonEntity>.mapToPokemonShowcase() =
    PokemonShowcase(results = this.map { it.mapToPokemon() })