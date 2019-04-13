package com.angelsit.kudagofeed.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class City (
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String
):  Serializable