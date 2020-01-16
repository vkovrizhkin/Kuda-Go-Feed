package com.angelsit.kudagofeed

import com.angelsit.kudagofeed.data.dto.City
import com.angelsit.kudagofeed.data.dto.event.Event
import com.angelsit.kudagofeed.data.dto.eventdetails.EventDetails
import com.angelsit.kudagofeed.repo.EventsRepo

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
            fun onGetEventsFinish(result: List<EventsRepo.EventPreviewEntity>)
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