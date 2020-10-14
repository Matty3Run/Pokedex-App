package com.test.pokedex.domain.model

import com.squareup.moshi.Json

/**
 * Represents all the available images of a Pokemon
 */
data class Sprites(
    val other: ImagesSources
)

data class ImagesSources(
    @Json(name = "dream_world")
    val dreamWorld: Image,
    @Json(name = "official-artwork")
    val officialArtwork: Image,
)

data class Image(
    @Json(name = "front_default")
    val frontDefault: String?
)