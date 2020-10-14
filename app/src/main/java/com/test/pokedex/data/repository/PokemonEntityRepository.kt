package com.test.pokedex.data.repository

import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.domain.model.PokemonShowcase

/**
 * The Pokemon repository interface, defines all the available methods
 */
interface PokemonEntityRepository {
    /**
     * Get the list of Pokemon
     */
    suspend fun getPokemonList(): PokemonShowcase

    /**
     * Get a new list of pokemon based on the given "paginated" url
     */
    suspend fun getMorePokemon(nextUrl: String): PokemonShowcase

    /**
     * Get details of a given Pokemon
     */
    suspend fun getPokemonDetails(pokemonName: String): Pokemon

}