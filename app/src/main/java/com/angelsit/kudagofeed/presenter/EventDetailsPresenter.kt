package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.model.EventDetails
import com.angelsit.kudagofeed.model.MockData
import com.angelsit.kudagofeed.view.EventDetailsActivity

class EventDetailsPresenter(private val mView: EventDetailsActivity): MainContract.Presenter.GetEventDetailsListener {
    override fun onGetEventDetailsFinish(result: EventDetails) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetEventDetailsFailed(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onResume(eventId: String){
        val result = MockData.getInfoDetail()
        mView.showDetails(result)
    }
}