package org.codejudge.application.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_restaurant.view.*
import org.codejudge.application.R
import org.codejudge.application.helper.appendCurrency
import org.codejudge.application.helper.hide
import org.codejudge.application.helper.loadImage
import org.codejudge.application.helper.show
import org.codejudge.application.model.Restaurant

/**
 * @author Rolbin, created on 13-11-2020.
 */
class RestaurantsAdapter(diffChecker: RestaurantDiffChecker) :
        ListAdapter<Restaurant, RestaurantsAdapter.RestaurantViewHolder>(diffChecker) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val restaurantView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_restaurant, parent, false)
        return RestaurantViewHolder(restaurantView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bindData(restaurant)
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRestaurantName = itemView.findViewById<TextView>(R.id.tvRestaurantName)
        private val tvRestaurantRating = itemView.findViewById<AppCompatTextView>(R.id.tvRating)
        private val tvRestaurantPricingLevel = itemView.findViewById<TextView>(R.id.tvRestaurantPricingLLevel)
        private val tvRestaurantOpenStatus = itemView.findViewById<TextView>(R.id.tvRestaurantOpenStatus)
        private val tvRestaurantOperationalStatus = itemView.findViewById<TextView>(R.id.tvRestaurantOperationalStatus)

        fun bindData(restaurant: Restaurant) {
            with(restaurant) {
                itemView.ivRestaurantIcon.loadImage(icon)
                tvRestaurantName.text = name
                rating?.let { ratingValue ->
                    setRestaurantRating(ratingValue)
                } ?: tvRestaurantRating.hide()
                setRestaurantPricing(getPriceLevel())
                when (isBusinessOperational(businessStatus)) {
                    true -> {
                        setAdditionalDetails(openingHours)
                    }
                    false -> {
                        tvRestaurantOpenStatus.hide()
                    }
                }
            }
        }

        private fun setRestaurantRating(rating: Double) {
            tvRestaurantRating.apply {
                val color = when (rating) {
                    in 4.5..5.0 -> R.color.rating_4_5
                    in 4.0..4.5 -> R.color.rating_4
                    in 3.5..4.0 -> R.color.rating_3_5
                    else -> R.color.rating_less_than_3_5
                }
                ViewCompat.setBackgroundTintList(this, ContextCompat.getColorStateList(context, color))
                text = rating.toString()
                show()
            }
        }

        private fun setRestaurantPricing(priceLevel: String?) {
            priceLevel?.let {
                tvRestaurantPricingLevel.show()
                tvRestaurantPricingLevel.text = it.appendCurrency()
            } ?: tvRestaurantPricingLevel.hide()
        }

        private fun isBusinessOperational(businessStatus: Restaurant.BusinessStatus?): Boolean {
            return when (businessStatus) {
                Restaurant.BusinessStatus.CLOSED_PERMANENTLY, Restaurant.BusinessStatus.CLOSED_TEMPORARILY -> {
                    tvRestaurantOperationalStatus.show()
                    tvRestaurantOperationalStatus.text = businessStatus.status
                    false
                }
                else -> {
                    tvRestaurantOperationalStatus.hide()
                    true
                }
            }
        }

        private fun setAdditionalDetails(openingHours: Restaurant.OpeningHours?) {
            val statusStringRes = if (openingHours?.openNow == true) R.string.restaurant_open else R.string.restaurant_closed
            tvRestaurantOpenStatus.apply {
                text = context.getString(statusStringRes)
                show()
            }
        }
    }
}

class RestaurantDiffChecker : DiffUtil.ItemCallback<Restaurant>() {

    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem.placeId == newItem.placeId

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
}