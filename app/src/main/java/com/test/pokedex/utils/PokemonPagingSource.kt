package com.test.pokedex.utils

import androidx.paging.PagingSource
import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.domain.usecase.GetPokemonList

/**
 * PagingSource used to paginate the results of the pokemon list API call
 */
class PokemonPagingSource(
    private val pokemonListUseCase: GetPokemonList
) : PagingSource<String, Pokemon>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Pokemon> {
        return try {
            val response = pokemonListUseCase.invoke(params.key)

            // Uses "previous" and "next" data retrieved from the server to progress in the pagination
            LoadResult.Page(
                data = response.results,
                prevKey = response.previous,
                nextKey = response.next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}