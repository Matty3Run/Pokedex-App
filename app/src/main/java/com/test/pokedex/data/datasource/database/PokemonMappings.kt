package com.test.pokedex.data.datasource.database

import com.test.pokedex.domain.model.Pokemon

fun Pokemon.mapToPokemonEntity() =
    PokemonEntity(
        name,
        sprites?.other?.dreamWorld?.frontDefault,
        sprites?.other?.officialArtwork?.frontDefault,
        types?.map { it.type.name },
        stats
    )

fun List<Pokemon>.mapToPokemonEntities(): List<PokemonEntity> =
    this.map { it.mapToPokemonEntity() }