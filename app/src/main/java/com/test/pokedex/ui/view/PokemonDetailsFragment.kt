package com.test.pokedex.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.test.pokedex.R
import com.test.pokedex.ui.uimodel.states.PokemonState
import com.test.pokedex.ui.view.adapter.ImagesPagerAdapter
import com.test.pokedex.ui.view.base.BaseFragment
import com.test.pokedex.ui.viewmodel.PokemonDetailsViewModel
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Represents the pokemon details fragment
 */
class PokemonDetailsFragment : BaseFragment() {

    private val viewModel: PokemonDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Listen to Uniflow events
        onEvents(viewModel) { event ->
            when (event.take()) {
                is UIEvent.Loading -> onLoading(true)
            }
        }

        // Listen to Uniflow states
        onStates(viewModel) { state ->
            when (state) {
                is PokemonState -> updateUI(state)
                is UIState.Failed -> onError()
            }
        }

        viewModel.retrievePokemonDetail((activity as PokemonDetailsActivity).pokemonName)
    }

    private fun onLoading(isLoading: Boolean) {
        super.loading(isLoading)
    }

    /**
     * On error, shows the "no data" view
     */
    private fun onError() {
        onLoading(false)
        scrollContainer.isVisible = false
        showEmptyView(getString(R.string.pokemon_details_no_data_message))
    }

    /**
     * Updates the UI according to the new state
     */
    private fun updateUI(state: PokemonState) {
        onLoading(false)

        // Images section
        if (state.images.isEmpty()) {
            imagesPager.isVisible = false
        } else {
            val pagerAdapter = ImagesPagerAdapter(requireContext(), state.images)
            imagesPager.adapter = pagerAdapter
        }

        // Types section
        if (state.types.isEmpty()) {
            typeSection.isVisible = false
        } else {
            typeTitle.text =
                getString(if (state.types.size == 1) R.string.pokemon_details_type else R.string.pokemon_details_types)
            type.text = state.types.joinToString(", ")
        }

        // Stats section
        if (state.types.isEmpty()) {
            statsSection.isVisible = false
        } else {
            val inflater =
                requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            state.stats.forEach { stat ->
                val viewLayout: View = inflater.inflate(R.layout.item_stat, statsContainer, false)

                val statName = viewLayout.findViewById(R.id.statName) as TextView
                val statValue = viewLayout.findViewById(R.id.statValue) as TextView

                statName.text = stat.first
                statValue.text = stat.second.toString()

                statsContainer.addView(viewLayout)
            }
        }

        // If no one of the three sections of the details are populated, we show the "no data" view
        if (!imagesPager.isVisible && !typeSection.isVisible && !statsSection.isVisible) {
            onError()
        }
    }

}