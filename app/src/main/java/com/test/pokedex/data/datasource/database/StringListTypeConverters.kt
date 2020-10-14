package com.test.pokedex.data.datasource.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Room type converter for lists of "String" objects
 */
class StringListTypesConverters {
    var moshi = Moshi.Builder().build()

    @TypeConverter
    fun listToJson(value: List<String>?): String? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return adapter.fromJson(value)
    }

}