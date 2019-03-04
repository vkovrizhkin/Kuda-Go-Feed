package com.angelsit.kudagofeed

import com.angelsit.kudagofeed.model.Event

interface MainContract {
    interface View{
        fun showLoader()
        fun showError()
        fun showEvents(events : List<Event>)

    }
    interface Presenter {
        fun onApplicationLaunch()
        fun onCityChange(newCity: String)
        fun onEventSelect(eventId: Int)
    }
    interface Repository {
        fun loadEvents(city: String): List<Event>
        fun saveCity (city: String)
    }
}