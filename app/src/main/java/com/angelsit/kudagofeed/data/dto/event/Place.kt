package com.angelsit.kudagofeed.data.dto.event

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("address")
    val address: String
)