package org.codejudge.application.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.codejudge.application.model.Restaurant
import org.codejudge.application.model.repo.RestaurantsRepo
import org.codejudge.application.network.Result
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * @author Rolbin, created on 13-11-2020.
 */
class RestaurantsViewModel(private val restaurantsRepo: RestaurantsRepo) : ViewModel() {
    private val _restaurantsSearchResult = MutableLiveData<List<Restaurant>>()
    val restaurantsSearchResult: LiveData<List<Restaurant>> = _restaurantsSearchResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val restaurants = liveData {
        when (val response = restaurantsRepo.getAllRestaurants()) {
            is Result.Success -> emit(response.data.restaurants)
            is Result.Failure -> handleError(response.throwable)
        }
    }

    fun searchRestaurants(searchQuery: String) {
        viewModelScope.launch {
            when (val response = restaurantsRepo.searchRestaurants(searchQuery)) {
                is Result.Success -> _restaurantsSearchResult.value = response.data.restaurants
                is Result.Failure -> handleError(response.throwable)
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        _errorMessage.value = when (throwable) {
            is IOException, is SocketTimeoutException -> "Please check your internet connection"
            else -> "Something went wrong"
        }
    }
}