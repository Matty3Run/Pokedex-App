package com.test.pokedex.ui.view

import android.os.Bundle
import androidx.navigation.navArgs
import com.test.pokedex.R
import com.test.pokedex.ui.view.base.BaseActivity
import java.util.*

/**
 * Represents the pokemon detail screen
 */
class PokemonDetailsActivity : BaseActivity() {

    private val args: PokemonDetailsActivityArgs by navArgs()

    lateinit var pokemonName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        pokemonName = args.pokemonName

        setupToolbar()
    }

    /**
     * Sets up the toolbar with the pokemon name
     */
    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = pokemonName.capitalize(Locale.getDefault())
    }

    /**
     * Manage navigating back using the toolbar "back" button
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Finishes the activity with a custom animation
     */
    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}