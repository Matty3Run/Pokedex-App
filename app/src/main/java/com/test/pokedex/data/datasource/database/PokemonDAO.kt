package com.test.pokedex.data.datasource.database

import androidx.room.*

@Dao
interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAll(entities: List<PokemonEntity>)

    @Update
    fun updatePokemon(entity: PokemonEntity)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun findPokemonByName(name: String): PokemonEntity

}