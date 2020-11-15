package org.codejudge.application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.codejudge.application.R
import org.codejudge.application.ui.viewmodel.RestaurantsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val restaurantsViewModel by viewModel<RestaurantsViewModel>()
    private val restaurantsAdapter by inject<RestaurantsAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvRestaurants = findViewById<RecyclerView>(R.id.rvRestaurants)
        val etSearchView = findViewById<SearchView>(R.id.edit_search)
        subscribeData()
        rvRestaurants.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantsAdapter
        }

        etSearchView.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(searchQuery: String?) = performRestaurantSearch(searchQuery)

                    override fun onQueryTextChange(searchQuery: String?) = performRestaurantSearch(searchQuery)
                }
        )
    }

    private fun subscribeData() {
        restaurantsViewModel.restaurants.observe(this) {
            restaurantsAdapter.submitList(it)
        }

        restaurantsViewModel.restaurantsSearchResult.observe(this) {
            restaurantsAdapter.submitList(it)
        }

        restaurantsViewModel.errorMessage.observe(this) {
            Snackbar.make(container, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun performRestaurantSearch(searchQuery: String?): Boolean {
        return searchQuery?.let {
            restaurantsViewModel.searchRestaurants(searchQuery)
            true
        } ?: false
    }
}
