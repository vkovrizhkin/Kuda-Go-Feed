package com.angelsit.kudagofeed.data.dto.event

class Event(
    val dates: List<Date>?,
    val description: String?,
    val id: Int,
    val images: List<Image>?,
    val place: Place?,
    val price: String?,
    val title: String

) {
    companion object {

        const val fields: String = "dates,title,price,place,images,description,id"
        const val expand: String = "place,price"
        const val textFormat: String = "text"

    }
}
