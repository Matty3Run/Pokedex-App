package com.test.pokedex.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.pokedex.R
import com.test.pokedex.domain.model.Pokemon
import java.util.*

/**
 * Paginated adapter for a list of pokemon. Extends PagingDataAdapter to manage all the needed operations to work
 * with Jetpack Paging Library v. 3.0
 */
class PokemonListAdapter(
    private val onPokemonSelected: (Pokemon) -> Unit
) : PagingDataAdapter<Pokemon, PokemonListAdapter.PokemonListHolder>(pokemonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonListHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        holder.bind(getItem(position), onPokemonSelected)
    }

    inner class PokemonListHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val pokemonName = item.findViewById<TextView>(R.id.pokemonName)
        private val pokemonContainer = item.findViewById<View>(R.id.pokemonContainer)

        fun bind(pokemon: Pokemon?, onClick: (Pokemon) -> Unit) {
            pokemonName.text = pokemon?.name?.capitalize(Locale.getDefault())
            pokemonContainer.setOnClickListener {
                if (pokemon != null) {
                    onClick(pokemon)
                }
            }
        }

    }

    companion object {
        // DiffUtil, used by the PagingDataAdapter to compare the new data with the previous data in the list
        private val pokemonComparator = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }
}