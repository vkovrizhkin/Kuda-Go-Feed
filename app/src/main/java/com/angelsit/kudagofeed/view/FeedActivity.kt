package com.angelsit.kudagofeed.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.event.Event
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

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    fun showEvents(eventList: List<Event>){

        events_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        events_recycler_view.adapter = FeedRecViewAdapter(eventList, this, eventItemOnClick)
        progress_bar.visibility = View.GONE
        events_recycler_view.visibility = View.VISIBLE

    }

    private val eventItemOnClick = { event: Event ->

/*        val intent = Intent(this, EventDetailsActivity::class.java)
        intent.putExtra("eventId", event.title) // todo поменять на id
        startActivity(intent)
        println(event.title)*/
    }

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
