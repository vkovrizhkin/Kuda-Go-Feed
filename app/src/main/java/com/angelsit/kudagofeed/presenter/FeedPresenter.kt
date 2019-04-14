package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.model.Api
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.Event
import com.angelsit.kudagofeed.model.MockData
import com.angelsit.kudagofeed.view.FeedActivity

class FeedPresenter (private val mView: FeedActivity): MainContract.Presenter.GetEventsListener {
    override fun onGetEventsFinish(result: List<Event>) {
        mView.showEvents(result)
    }

    override fun onGetEventsFailed(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onCitySelected(city: City){
        mView.updateSelectedCity(city)
    }

    fun onResume(){
        val eventList = MockData.getEvents(mView)
        Api.getEvents("msk", this)
    }
}