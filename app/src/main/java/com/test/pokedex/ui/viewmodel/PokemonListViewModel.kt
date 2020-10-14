package com.test.pokedex.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.domain.usecase.GetPokemonList
import com.test.pokedex.utils.PokemonPagingSource
import io.uniflow.androidx.flow.AndroidDataFlow
import kotlinx.coroutines.flow.Flow

/**
 * Represents the Pokemon List ViewModel, make use of Kotlin Flow
 */
class PokemonListViewModel(private val getPokemonList: GetPokemonList) : AndroidDataFlow() {

    companion object {
        const val PAGINATION_OFFSET = 20
    }

    // Flow used to page the pokemon lists retrieved from the server
    val pagingDataFlow: Flow<PagingData<Pokemon>> =
        Pager(PagingConfig(pageSize = PAGINATION_OFFSET)) {
            PokemonPagingSource(getPokemonList)
        }.flow.cachedIn(viewModelScope)

}