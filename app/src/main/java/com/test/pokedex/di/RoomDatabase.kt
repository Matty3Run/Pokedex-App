package com.test.pokedex.di

import androidx.room.Room
import com.test.pokedex.data.datasource.database.PokemonDatabase
import com.test.pokedex.data.repository.PokemonEntityPersistentRepositoryImpl
import com.test.pokedex.data.repository.PokemonEntityRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Room Database Module, used to store data in the Room database
 */
val roomDatabaseModule = module {

    // Pokemon Room Data Repository
    single<PokemonEntityRepository>(override = true) {
        PokemonEntityPersistentRepositoryImpl(get(), get())
    }
    // Room Database
    single {
        Room.databaseBuilder(androidApplication(), PokemonDatabase::class.java, "pokemon-db")
            .allowMainThreadQueries()
            .build()
    }
    // Exposing PokemonDAO
    single { get<PokemonDatabase>().pokemonDAO() }
}
