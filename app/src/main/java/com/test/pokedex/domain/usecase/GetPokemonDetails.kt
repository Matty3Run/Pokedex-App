package com.test.pokedex.domain.usecase

import com.test.pokedex.data.repository.PokemonEntityRepository
import com.test.pokedex.domain.model.Pokemon

/**
 * Gets pokemon details using the given pokemon name
 */
class GetPokemonDetails(private val pokemonEntityRepository: PokemonEntityRepository) {

    suspend operator fun invoke(pokemonName: String): Pokemon {
        return pokemonEntityRepository.getPokemonDetails(pokemonName)
    }

}