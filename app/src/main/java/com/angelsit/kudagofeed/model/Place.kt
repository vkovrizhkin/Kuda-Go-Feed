package com.angelsit.kudagofeed.model

data class Place(
    val address: String,
    val coords: Coords,
    val id: Int,
    val is_closed: Boolean,
    val is_stub: Boolean,
    val location: String,
    val phone: String,
    val site_url: String,
    val slug: String,
    val subway: String,
    val title: String
)