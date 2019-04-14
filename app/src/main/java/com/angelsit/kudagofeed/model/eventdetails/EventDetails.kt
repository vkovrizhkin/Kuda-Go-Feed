package com.angelsit.kudagofeed.model.eventdetails

import com.angelsit.kudagofeed.model.event.Date
import com.angelsit.kudagofeed.model.event.Image
import com.angelsit.kudagofeed.model.event.Place

data class EventDetails(
    val age_restriction: String,
    val body_text: String,
    val categories: List<String>,
    val dates: List<Date>,
    val description: String,
    val id: Int,
    val images: List<Image>,
    val is_free: Boolean,
    val participants: List<Any>,
    val place: Place,
    val price: String,
    val publication_date: Int,
    val short_title: String,
    val slug: String,
    val title: String
) {
    companion object {
        const val expand = "place"
        const val textFormat = "text"
    }
}