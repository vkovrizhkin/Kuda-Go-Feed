package com.angelsit.kudagofeed.model.event

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("address")
    val address: String
)