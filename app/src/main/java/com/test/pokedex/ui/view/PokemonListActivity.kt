package com.test.pokedex.ui.view

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.test.pokedex.R
import com.test.pokedex.ui.view.base.BaseActivity

/**
 * Represents the pokemon list screen
 */
class PokemonListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
    }

}