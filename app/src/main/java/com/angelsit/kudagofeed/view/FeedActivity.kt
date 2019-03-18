package com.angelsit.kudagofeed.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.presenter.FeedPresenter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private val CITY_REQUEST_CODE = 1

    private var selectedCity = City("msk", "Москва")

    private val presenter = FeedPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        change_city_button.setOnClickListener {
            onChangeCityClick()
        }
        change_city_button.text = "Москва"
    }

    //TODO  вернуть го
    // род ...
    private val onChangeCityClick = fun() {
        val intent = Intent(this, CityListActivity::class.java)
        startActivityForResult(intent, CITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) return
        when (requestCode) {
            CITY_REQUEST_CODE -> {
                val city = data.getSerializableExtra("city") as City

                presenter.onCitySelected(city)
            }
        }
    }

    fun updateSelectedCity(city: City) {
        selectedCity = city
        change_city_button.text = city.name
    }
}
