package com.angelsit.kudagofeed.view

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.presenter.CitiesPresenter
import kotlinx.android.synthetic.main.activity_city_list.*
import android.content.Intent
import android.view.View


class CityListActivity : AppCompatActivity() {

    private val presenter = CitiesPresenter(this)

    private val currentCity = City("msk", "Москва")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        cities_toolbar.setNavigationOnClickListener { finish() }

        swipe_to_refresh.setOnRefreshListener { presenter.onUpdate() }
        swipe_to_refresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent ,R.color.colorPrimaryDark)


    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    fun showCityList(cityList: List<City>) {
        if(swipe_to_refresh.isRefreshing){
            swipe_to_refresh.isRefreshing = false
        }
        cities_rec_view.layoutManager = LinearLayoutManager(this)
        cities_rec_view.adapter = CityRecViewAdapter(cityList, this, selectCity, currentCity)
        progress_bar.visibility = View.GONE
        cities_rec_view.visibility = View.VISIBLE

    }
    fun showLoading(){
        progress_bar.visibility = View.VISIBLE
        cities_rec_view.visibility = View.GONE
    }

    private val selectCity = { city: City ->
        println(city.name)
        val intent = Intent()
        intent.putExtra("city", city)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
