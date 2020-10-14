package com.test.pokedex

import android.app.Application
import com.test.pokedex.di.databasePokedexApp
import io.uniflow.androidx.logger.AndroidMessageLogger
import io.uniflow.core.logger.UniFlowLogger
import io.uniflow.core.logger.UniFlowLogger.FUN_TAG
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Represents the Application
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Starts Koin, used to inject the dependencies
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(databasePokedexApp) // Use "databasePokedexApp" to work online and offline, use "onlinePokedexApp" to work only online
        }

        // Initializes Uniflow library
        UniFlowLogger.init(AndroidMessageLogger(FUN_TAG, true))
    }
}
