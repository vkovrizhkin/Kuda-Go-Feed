package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.model.api.Api
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.event.Event
import com.angelsit.kudagofeed.model.sharedpreference.SharedPreferenceManager
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
        Api.getEvents(city.slug, this)
    }

    fun onResume() {
        mView.showLoading()
        Api.getEvents(mView.selectedCity!!.slug, this)
    }
    fun onUpdate() {
        Api.getEvents(mView.selectedCity!!.slug, this)
    }

    fun onCreate() {
        val selectedCity = SharedPreferenceManager.getSelectedCity(mView)
        mView.initSelectedCity(selectedCity)
    }
}