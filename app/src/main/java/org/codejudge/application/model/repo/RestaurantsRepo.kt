package org.codejudge.application.model.repo

import org.codejudge.application.model.Restaurants
import org.codejudge.application.network.Result

/**
 * @author Rolbin, created on 13-11-2020.
 */
interface RestaurantsRepo {

    suspend fun getAllRestaurants(): Result<Restaurants>

    suspend fun searchRestaurants(searchQuery: String): Result<Restaurants>
}