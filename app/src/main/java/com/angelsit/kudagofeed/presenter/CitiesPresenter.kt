package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.model.Api
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.MockData
import com.angelsit.kudagofeed.view.CityListActivity

class CitiesPresenter(private val mView: CityListActivity): MainContract.Presenter.GetCitiesListener {
    override fun onGetCityFinish(result: List<City>) {
        mView.showCityList(result)
    }

    override fun onGetCityFailed(t: Throwable) {
        //To change body of created functions use File | Settings | File Templates.
    }

    fun onResume(){
        Api.getCities(this)
        //mView.showCityList(MockData.getLocations(mView))

    }
}