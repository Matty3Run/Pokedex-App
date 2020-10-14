package com.test.pokedex.domain.usecase

import com.test.pokedex.data.repository.PokemonEntityRepository
import com.test.pokedex.domain.model.PokemonShowcase

/**
 * Gets a list of pokemon
 */
class GetPokemonList(private val pokemonEntityRepository: PokemonEntityRepository) {

    /**
     * If "nextUrl" is null, we don't have a "next" URL because is the first API call to retrieve a pokemon list,
     * so we retrieve the first pokemon list from the web service.
     * Otherwise if "nextUrl" is populated, we can use this URL to retrieve more pokemon, to paginate the results
     */
    suspend operator fun invoke(nextUrl: String? = null): PokemonShowcase {
        return if (nextUrl.isNullOrEmpty()) pokemonEntityRepository.getPokemonList() else pokemonEntityRepository.getMorePokemon(
            nextUrl
        )
    }

}