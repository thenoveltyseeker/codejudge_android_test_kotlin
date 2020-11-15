package org.codejudge.application

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Rolbin, created on 13-11-2020.
 */
class RestaurantApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RestaurantApp)
            modules(listOf(networkModule, restaurantModule))
        }
    }

}