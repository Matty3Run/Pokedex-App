package com.test.pokedex.data.datasource.webservice

import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.domain.model.PokemonShowcase
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Pokemon datasource - contains all the API calls
 */
interface PokemonDataSource {

    @GET("/api/v2/pokemon")
    @Headers("Content-type: application/json")
    fun getPokemonList(): Deferred<PokemonShowcase>

    @GET
    @Headers("Content-type: application/json")
    fun getMorePokemon(@Url url: String?): Deferred<PokemonShowcase>

    @GET("/api/v2/pokemon/{name}")
    @Headers("Content-type: application/json")
    fun getPokemonDetails(@Path("name") pokemonName: String): Deferred<Pokemon>

}
