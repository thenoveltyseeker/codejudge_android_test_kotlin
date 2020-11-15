package org.codejudge.application.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Rolbin, created on 13-11-2020.
 */
@JsonClass(generateAdapter = true)
data class Restaurants(
        @Json(name = "results")
        val restaurants: List<Restaurant>
)

@JsonClass(generateAdapter = true)
data class Restaurant(
        @Json(name = "business_status")
        val businessStatus: BusinessStatus?,
        @Json(name = "formatted_address")
        val formattedAddress: String?,
        @Json(name = "icon")
        val icon: String?,
        @Json(name = "id")
        val id: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "opening_hours")
        val openingHours: OpeningHours?,
        @Json(name = "place_id")
        val placeId: String,
        @Json(name = "plus_code")
        val plusCode: PlusCode?,
        @Json(name = "price_level")
        val priceLevel: Int?,
        @Json(name = "rating")
        val rating: Double?,
        @Json(name = "reference")
        val reference: String?,
        @Json(name = "types")
        val types: List<String>?,
        @Json(name = "user_ratings_total")
        val userRatingsTotal: Int?
) {

    fun getPriceLevel(): String? {
        val price = PriceLevel.values().find { price ->
            priceLevel == price.ordinal
        }
        return price?.priceStatus
    }

    @JsonClass(generateAdapter = true)
    data class OpeningHours(
            @Json(name = "open_now")
            val openNow: Boolean?
    )

    @JsonClass(generateAdapter = true)
    data class PlusCode(
            @Json(name = "compound_code")
            val compoundCode: String?,
            @Json(name = "global_code")
            val globalCode: String?
    )

    enum class BusinessStatus(val status: String) {
        OPERATIONAL("Operational"),
        CLOSED_TEMPORARILY("Temporarily closed"),
        CLOSED_PERMANENTLY("Permanently closed")
    }

    enum class PriceLevel(val priceStatus: String) {
        FREE("Free"),
        INEXPENSIVE("Inexpensive"),
        MODERATE("Moderately Expensive"),
        EXPENSIVE("Expensive"),
        VERY_EXPENSIVE("Very Expensive"),
    }
}

