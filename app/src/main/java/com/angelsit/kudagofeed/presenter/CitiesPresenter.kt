package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.data.api.Api
import com.angelsit.kudagofeed.data.dto.City
import com.angelsit.kudagofeed.view.CityListActivity

class CitiesPresenter(private val mView: CityListActivity): MainContract.Presenter.GetCitiesListener {
    override fun onGetCityFinish(result: List<City>) {
        mView.showCityList(result)
    }

    override fun onGetCityFailed(t: Throwable) {
        //To change body of created functions use File | Settings | File Templates.
    }

    fun onResume(){
        mView.showLoading()
        Api.getCities(this)
    }
    fun onUpdate(){
        Api.getCities(this)
    }
}