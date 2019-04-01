package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.model.EventDetails
import com.angelsit.kudagofeed.model.MockData
import com.angelsit.kudagofeed.view.EventDetailsActivity

class EventDetailsPresenter(private val mView: EventDetailsActivity) {

    fun onResume(eventId: String){
        val result = MockData.getInfoDetail()
        mView.showDetails(result)
    }
}