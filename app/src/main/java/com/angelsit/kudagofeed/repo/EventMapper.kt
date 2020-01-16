package com.angelsit.kudagofeed.repo

import com.angelsit.kudagofeed.data.dto.event.Event

object EventMapper {

    fun previewFromDto(event: Event) = EventsRepo.EventPreviewEntity(
        event.id,
        event.title,
        event.description ?: "",
        event.images?.get(0)?.image ?: ""
    )
}