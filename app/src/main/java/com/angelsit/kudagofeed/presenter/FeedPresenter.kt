package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.data.api.Api
import com.angelsit.kudagofeed.data.dto.City
import com.angelsit.kudagofeed.data.dto.event.Event
import com.angelsit.kudagofeed.data.sharedpreference.SharedPreferenceManager
import com.angelsit.kudagofeed.repo.EventsRepo
import com.angelsit.kudagofeed.view.FeedActivity

class FeedPresenter(private val mView: FeedActivity) : MainContract.Presenter.GetEventsListener {

    override fun onGetEventsFinish(result: List<Event>) {
        mView.showEvents(result)
    }

    override fun onGetEventsFailed(t: Throwable) {
        //To change body of created functions use File | Settings | File Templates.
    }

    fun onCitySelected(city: City) {
        mView.showLoading()
        SharedPreferenceManager.saveSelectedCity(mView, city)
        mView.updateSelectedCity(city)
        EventsRepo.getEvents(city.slug, this)
    }

    fun onResume() {
        mView.showLoading()
        EventsRepo.getEvents(mView.selectedCity!!.slug, this)
    }

    fun onUpdate() {
        EventsRepo.getEvents(mView.selectedCity!!.slug, this)
    }

    fun onCreate() {
        val selectedCity = SharedPreferenceManager.getSelectedCity(mView)
        mView.initSelectedCity(selectedCity)
    }
}