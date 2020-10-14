package com.test.pokedex.data.datasource.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.pokedex.domain.model.Stats

/**
 * Room type converter for lists of "Stats" objects
 */
class StatsListTypesConverters {
    var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun listToJson(value: List<Stats>?): String? {
        val type = Types.newParameterizedType(List::class.java, Stats::class.java)
        val adapter: JsonAdapter<List<Stats>> = moshi.adapter(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Stats>? {
        val type = Types.newParameterizedType(List::class.java, Stats::class.java)
        val adapter: JsonAdapter<List<Stats>> = moshi.adapter(type)
        return adapter.fromJson(value)
    }

}