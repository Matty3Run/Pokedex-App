package com.test.pokedex.domain.model

/**
 * Represents a pokemon list, together with next and previous url data
 */
data class PokemonShowcase(val next: String? = null, val previous: String? = null, val results: List<Pokemon>)