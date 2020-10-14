package com.test.pokedex.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.test.pokedex.R
import com.test.pokedex.domain.model.Pokemon
import com.test.pokedex.ui.view.adapter.PokemonListAdapter
import com.test.pokedex.ui.view.adapter.PokemonLoadStateAdapter
import com.test.pokedex.ui.view.base.BaseFragment
import com.test.pokedex.ui.viewmodel.PokemonListViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Represents the pokemon list fragment
 */
class PokemonListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: PokemonListViewModel by viewModel()

    private var pokemonAdapter: PokemonListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListView()

        setupRefreshLayout()

        // Starts retrieving paginated data using Kotlin's Flow
        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest { list ->
                pokemonAdapter?.submitData(list)
            }
        }
    }

    /**
     * Sets up the adapter and the pokemon list
     */
    private fun setupListView() {
        pokemonAdapter = PokemonListAdapter(::onPokemonSelected)

        pokemonList.apply {
            layoutManager = LinearLayoutManager(context)

            // Link the PokemonLoadStateAdapter with the main adapter to show "progress" layout as footer
            adapter = pokemonAdapter?.withLoadStateFooter(
                footer = PokemonLoadStateAdapter { pokemonAdapter?.retry() }
            )
        }

        // Listens to the adapter "load" states
        pokemonAdapter?.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                // Shows loading overlay if state is "Loading"
                if (!refreshLayout.isRefreshing) {
                    onLoading(true)
                }
            } else {
                refreshLayout.isRefreshing = false
                onLoading(false)

                when (loadState.refresh) {
                    // Shows "No data" view if state is "Error"
                    is LoadState.Error -> {
                        pokemonList.isVisible = false
                        showEmptyView(getString(R.string.pokemon_list_no_data_message))
                    }
                    else -> {
                        // Otherwise shows the adapter
                        hideEmptyView()
                        pokemonList.isVisible = true
                    }
                }
            }
        }
    }

    /**
     * Sets up the refresh listener
     */
    private fun setupRefreshLayout() {
        refreshLayout.setOnRefreshListener(this)
        refreshLayout.setColorSchemeColors(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimary
            )
        )
    }

    /**
     * Navigate to the pokemon details screen on pokemon click
     */
    private fun onPokemonSelected(selectedPokemon: Pokemon) {
        val action =
            PokemonListFragmentDirections.actionListFragmentToDetailActivity(selectedPokemon.name)

        findNavController().navigate(action)
    }

    private fun onLoading(isLoading: Boolean) {
        super.loading(isLoading)
    }

    override fun onRefresh() {
        pokemonAdapter?.refresh()
    }

}