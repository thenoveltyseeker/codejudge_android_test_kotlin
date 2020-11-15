package org.codejudge.application.model.repo

import org.codejudge.application.model.RestaurantApi
import org.codejudge.application.model.Restaurants
import org.codejudge.application.network.NetworkRequestFactory
import org.codejudge.application.network.Result

/**
 * @author Rolbin, created on 13-11-2020.
 */
class RestaurantsRepoImpl(
        private val networkRequestFactory: NetworkRequestFactory,
        private val restaurantApi: RestaurantApi
) : RestaurantsRepo {

    override suspend fun getAllRestaurants(): Result<Restaurants> {
        return networkRequestFactory.performRequest {
            restaurantApi.getAllRestaurants(getQueryParams())
        }
    }

    override suspend fun searchRestaurants(searchQuery: String): Result<Restaurants> {
        return networkRequestFactory.performRequest {
            val params = getQueryParams()
            params["keyword"] = searchQuery
            restaurantApi.searchRestaurants(params)
        }
    }

    private fun getQueryParams(): HashMap<String, String> {
        val queryParams = hashMapOf<String, String>()
        queryParams["location"] = "47.6204,-122.3491"
        queryParams["radius"] = "2500"
        queryParams["type"] = "restaurant"
        queryParams["key"] = "AIzaSyDhHzf6y1brSirE2hPeMjTqSBYE73pzukM"
        return queryParams
    }
}