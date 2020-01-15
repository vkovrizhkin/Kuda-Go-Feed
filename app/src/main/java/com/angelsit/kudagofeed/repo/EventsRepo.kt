package com.angelsit.kudagofeed.repo

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.data.api.Api

object EventsRepo {

    fun getEvents(citySlug: String, listener: MainContract.Presenter.GetEventsListener) {
        Api.getEvents(citySlug, listener)
    }
}