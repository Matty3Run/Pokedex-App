package com.test.pokedex.data.repository

import com.test.pokedex.data.datasource.webservice.PokemonDataSource
import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.domain.model.PokemonShowcase

/**
 * Repository implementation that uses only network api calls to retrieve data
 */
class PokemonEntityRepositoryImpl(private val pokemonDatasource: PokemonDataSource) :
    PokemonEntityRepository {

    override suspend fun getPokemonDetails(pokemonName: String): Pokemon = pokemonDatasource.getPokemonDetails(pokemonName).await()

    override suspend fun getPokemonList(): PokemonShowcase = pokemonDatasource.getPokemonList().await()

    override suspend fun getMorePokemon(nextUrl: String): PokemonShowcase = pokemonDatasource.getMorePokemon(nextUrl).await()

}