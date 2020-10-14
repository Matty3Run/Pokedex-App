package com.test.pokedex.domain.model

/**
 * Represents a Pokemon
 */
data class Pokemon(
    val name: String,
    val sprites: Sprites?,
    val types: List<TypeSlot>?,
    val stats: List<Stats>?
)