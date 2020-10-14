package com.test.pokedex.di

import com.test.pokedex.data.repository.PokemonEntityRepository
import com.test.pokedex.data.repository.PokemonEntityRepositoryImpl
import com.test.pokedex.domain.usecase.GetPokemonDetails
import com.test.pokedex.domain.usecase.GetPokemonList
import com.test.pokedex.ui.viewmodel.PokemonDetailsViewModel
import com.test.pokedex.ui.viewmodel.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * App Components
 */
val appModule = module {
    // ViewModels
    viewModel { PokemonListViewModel(get()) }
    viewModel { PokemonDetailsViewModel(get()) }

    // Use cases
    factory { GetPokemonList(get()) }
    factory { GetPokemonDetails(get()) }

    // Data Repository
    single<PokemonEntityRepository> { PokemonEntityRepositoryImpl(get()) }
}

// Gather all app modules

// Using this module, the app works only online
val onlinePokedexApp = appModule + remoteWebServiceModule

// Using this module, the app works both online and offline, using data stored in Room Database
val databasePokedexApp = onlinePokedexApp + roomDatabaseModule