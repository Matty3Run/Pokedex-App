package com.test.pokedex.domain.model

/**
 * Represents a type of a Pokemon
 */
data class TypeSlot(
    val slot: Int?,
    val type: Type
)

data class Type(
    val name: String
)