package com.test.pokedex.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PokemonEntity::class], version = 1)
@TypeConverters(StringListTypesConverters::class, StatsListTypesConverters::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO
}