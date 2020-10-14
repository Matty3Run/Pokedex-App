package com.test.pokedex.domain.model

import com.squareup.moshi.Json

/**
 * Represents a stat of a Pokemon
 */
data class Stats(
    @Json(name = "base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String
)