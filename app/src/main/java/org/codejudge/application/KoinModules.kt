package org.codejudge.application

import okhttp3.OkHttpClient
import org.codejudge.application.helper.ConfigHelper
import org.codejudge.application.network.NetworkRequestFactory
import org.codejudge.application.model.RestaurantApi
import org.codejudge.application.model.repo.RestaurantsRepo
import org.codejudge.application.model.repo.RestaurantsRepoImpl
import org.codejudge.application.ui.RestaurantsAdapter
import org.codejudge.application.ui.RestaurantDiffChecker
import org.codejudge.application.ui.viewmodel.RestaurantsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author Rolbin, created on 14-11-2020.
 */
val networkModule = module {
    single<Converter.Factory> { MoshiConverterFactory.create() }
    single { buildHttpClient() }
    single {
        val baseUrl = ConfigHelper.getConfigValue(androidApplication(), "api_url")
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(get())
                .client(get())
                .build()
    }
    single { NetworkRequestFactory((get())) }
}

private fun buildHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    return httpClient.build()
}

val restaurantModule = module {
    single { get<NetworkRequestFactory>().createApiService(RestaurantApi::class.java) }
    factory<RestaurantsRepo> { RestaurantsRepoImpl(get(), get()) }
    factory { RestaurantDiffChecker() }
    factory { RestaurantsAdapter(get()) }
    viewModel { RestaurantsViewModel(get()) }
}