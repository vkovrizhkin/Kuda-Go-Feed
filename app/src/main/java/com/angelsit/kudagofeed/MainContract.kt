package com.angelsit.kudagofeed

import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.event.Event
import com.angelsit.kudagofeed.model.EventDetails

interface MainContract {
    interface View{

        fun showLoader()
        fun showError()
        fun showEvents(events : List<Event>)

    }
    interface Presenter {

        interface GetCitiesListener{
            fun onGetCityFinish(result: List<City>)
            fun onGetCityFailed(t: Throwable)
        }
        interface GetEventsListener{
            fun onGetEventsFinish(result: List<Event>)
            fun onGetEventsFailed(t: Throwable)
        }
        interface GetEventDetailsListener{
            fun onGetEventDetailsFinish(result: EventDetails)
            fun onGetEventDetailsFailed(t: Throwable)
        }
        fun onApplicationLaunch()
        fun onCityChange(newCity: String)
        fun onEventSelect(eventId: Int)
    }
    interface Repository {
        fun loadEvents(city: String): List<Event>
        fun saveCity (city: String)
    }
}