package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.data.sources.api.Api
import com.angelsit.kudagofeed.model.eventdetails.EventDetails
import com.angelsit.kudagofeed.view.EventDetailsActivity

class EventDetailsPresenter(private val mView: EventDetailsActivity): MainContract.Presenter.GetEventDetailsListener {
    override fun onGetEventDetailsFinish(result: EventDetails) {
        mView.showDetails(result)
    }

    override fun onGetEventDetailsFailed(t: Throwable) {
        //To change body of created functions use File | Settings | File Templates.
    }

    fun onResume(eventId: String){

        mView.showLoading()
        Api.getEventDetails(eventId, this)
    }
    fun onUpdate(eventId: String){

        Api.getEventDetails(eventId, this)
    }
}