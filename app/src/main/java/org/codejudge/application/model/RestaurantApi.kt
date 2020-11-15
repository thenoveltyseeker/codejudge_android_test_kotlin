package org.codejudge.application.model

import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Rolbin, created on 13-11-2020.
 */
interface RestaurantApi {

    @GET("json")
    suspend fun getAllRestaurants(@QueryMap params: Map<String, String>): Restaurants

    @GET("json")
    suspend fun searchRestaurants(@QueryMap params: Map<String, String>): Restaurants
}