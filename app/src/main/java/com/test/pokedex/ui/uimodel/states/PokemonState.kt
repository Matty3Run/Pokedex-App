package com.test.pokedex.ui.uimodel.states

import io.uniflow.core.flow.data.UIState

/**
 * Represent the Uniflow UIState of a Pokemon
 */
data class PokemonState(
    val name: String,
    val images: List<String>,
    val types: List<String>,
    val stats: List<Pair<String, Int>>
) : UIState()