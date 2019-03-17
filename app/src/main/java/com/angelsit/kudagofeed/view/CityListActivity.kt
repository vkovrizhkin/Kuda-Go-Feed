package com.angelsit.kudagofeed.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.presenter.CitiesPresenter
import kotlinx.android.synthetic.main.activity_city_list.*

class CityListActivity : AppCompatActivity() {

    private val presenter = CitiesPresenter(this)

    private val currentCity = City("msk", "Москва")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)


    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    fun showCityList(cityList: List<City>) {
        cities_rec_view.layoutManager = LinearLayoutManager(this)
        cities_rec_view.adapter = CityRecViewAdapter(cityList, this, selectCity, currentCity)

    }

    private val selectCity = { city: City ->
        println(city.name)
    }
}
