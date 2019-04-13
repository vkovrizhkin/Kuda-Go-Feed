package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.model.Api
import com.angelsit.kudagofeed.model.MockData
import com.angelsit.kudagofeed.view.CityListActivity

class CitiesPresenter(private val mView: CityListActivity) {

    fun onResume(){
        mView.showCityList(MockData.getLocations(mView))
        Api.getCities()
    }
}