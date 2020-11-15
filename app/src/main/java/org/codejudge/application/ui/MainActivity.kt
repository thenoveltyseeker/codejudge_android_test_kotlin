package org.codejudge.application.ui

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
        val etSearchView = findViewById<EditText>(R.id.edit_search)
        subscribeData()
        rvRestaurants.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantsAdapter
        }

        etSearchView.addTextChangedListener {
            performRestaurantSearch(it.toString())
        }
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

    private fun performRestaurantSearch(searchQuery: String) {
        restaurantsViewModel.searchRestaurants(searchQuery)
    }
}
