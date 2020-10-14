package com.test.pokedex.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.pokedex.data.datasource.webservice.PokemonDataSource
import com.test.pokedex.di.Properties.SERVER_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Remote Web Service Module, used to retrieve data from the server
 */
val remoteWebServiceModule = module {
    // provided web components
    single { createOkHttpClient() }
    // Fill property
    single { createWebService<PokemonDataSource>(get(), getProperty(SERVER_URL)) }
}

object Properties {
    const val SERVER_URL = "SERVER_URL"
}

/**
 * Returns a simple OkHttpClient with logging enabled
 */
fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
}

/**
 * Sets up the Retrofit Builder and creates the web service
 */
inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    return retrofit.create(T::class.java)
}
