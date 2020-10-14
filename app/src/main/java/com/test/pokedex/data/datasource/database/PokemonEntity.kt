package com.test.pokedex.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.pokedex.domain.model.Stats

@Entity(tableName = "pokemon")
data class PokemonEntity(
        @PrimaryKey
        val name: String,
        val dreamWorldImage: String?,
        val officialArtworkImage: String?,
        val types: List<String>?,
        val stats: List<Stats>?
)
