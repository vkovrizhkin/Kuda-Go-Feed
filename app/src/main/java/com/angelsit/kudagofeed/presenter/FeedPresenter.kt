package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.MockData
import com.angelsit.kudagofeed.view.FeedActivity

class FeedPresenter (val mView: FeedActivity) {

    fun onCitySelected(city: City){
        mView.updateSelectedCity(city)
    }

    fun onResume(){
        val eventList = MockData.getEvents(mView)
        mView.showEvents(eventList)
    }
}