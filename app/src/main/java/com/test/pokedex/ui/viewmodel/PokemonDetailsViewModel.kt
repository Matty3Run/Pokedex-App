package com.test.pokedex.ui.viewmodel

import com.test.pokedex.domain.usecase.GetPokemonDetails
import com.test.pokedex.ui.uimodel.mappings.mapToPokemonState
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState

/**
 * Represents the Pokemon Details ViewModel, make use of Uniflow lib
 */
class PokemonDetailsViewModel(private val getPokemonDetails: GetPokemonDetails) :
    AndroidDataFlow() {

    /**
     * Retrieves the pokemon details
     */
    fun retrievePokemonDetail(pokemonName: String) = action(
        onAction = {
            sendEvent { UIEvent.Loading }
            val pokemon = getPokemonDetails(pokemonName)
            setState { pokemon.mapToPokemonState() }
        },
        onError = { _, _ -> setState { UIState.Failed() } })

}