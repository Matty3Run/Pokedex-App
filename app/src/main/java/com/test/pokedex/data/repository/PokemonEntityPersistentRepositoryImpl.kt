package com.test.pokedex.data.repository

import com.test.pokedex.data.datasource.database.PokemonDAO
import com.test.pokedex.data.datasource.database.mapToPokemonEntities
import com.test.pokedex.data.datasource.database.mapToPokemonEntity
import com.test.pokedex.data.datasource.webservice.PokemonDataSource
import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.domain.model.PokemonShowcase

/**
 * Repository implementation that uses local data if network isn't available or server returns an error
 */
class PokemonEntityPersistentRepositoryImpl(
    private val pokemonDatasource: PokemonDataSource,
    private val pokemonDAO: PokemonDAO
) : PokemonEntityRepository {

    override suspend fun getPokemonDetails(pokemonName: String): Pokemon {
        // Try retrieving pokemon datails from network, saving data to Room database
        return try {
            val pokemonDetail = pokemonDatasource.getPokemonDetails(pokemonName).await()

            pokemonDAO.updatePokemon(pokemonDetail.mapToPokemonEntity())

            pokemonDetail
        } catch (ex: Exception) {
            // If network call fails, use pokemon local data
            pokemonDAO.findPokemonByName(pokemonName).mapToPokemon()
        }
    }

    override suspend fun getPokemonList(): PokemonShowcase {
        // Try retrieving pokemon list from network, saving data to Room database
        return try {
            val pokemonShowcase = pokemonDatasource.getPokemonList().await()

            pokemonDAO.saveAll(pokemonShowcase.results.mapToPokemonEntities())

            pokemonShowcase
        } catch (ex: Exception) {
            // If network call fails, use pokemon local data
            pokemonDAO.getAllPokemon().mapToPokemonShowcase()
        }
    }

    override suspend fun getMorePokemon(nextUrl: String): PokemonShowcase {
        // Try retrieving more pokemon from network, saving data to Room database
        return try {
            val pokemonShowcase = pokemonDatasource.getMorePokemon(nextUrl).await()

            pokemonDAO.saveAll(pokemonShowcase.results.mapToPokemonEntities())

            pokemonShowcase
        } catch (ex: Exception) {
            // If network call fails, use pokemon local data
            pokemonDAO.getAllPokemon().mapToPokemonShowcase()
        }
    }

}