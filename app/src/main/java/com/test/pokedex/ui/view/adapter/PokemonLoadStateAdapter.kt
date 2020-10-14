package com.test.pokedex.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.pokedex.R
import kotlinx.android.synthetic.main.item_paging.view.*

/**
 * A Load State Adapter for the PokemonListAdapter, used to manage the "progress" item at the bottom of the list,
 * useful to show to the user when the pagination is in progress, and to show errors, if any.
 * "retry" function allows the user to retry the last web service call if the pagination fails for some reason
 */
class PokemonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progress = holder.itemView.progress
        val retryButton = holder.itemView.retryButton
        val errorLabel = holder.itemView.errorMessage

        progress.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState !is LoadState.Loading
        errorLabel.isVisible = loadState !is LoadState.Loading

        retryButton.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_paging, parent, false)
        )
    }

    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view)
}