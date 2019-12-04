package com.angelsit.kudagofeed.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.event.Event
import com.angelsit.kudagofeed.presenter.FeedPresenter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {
    companion object {
        private const val CITY_REQUEST_CODE = 1
    }

    private val eventItemOnClick = { event: Event ->

        val intent = Intent(this, EventDetailsActivity::class.java)
        intent.putExtra(EventDetailsActivity.EVENT_ID_EXTRA, event.id.toString())
        startActivity(intent)
    }

    private var eventList: MutableList<Event> = mutableListOf()

    var adapter: FeedRecViewAdapter? = null

    var selectedCity: City? = null
    var layoutManager: androidx.recyclerview.widget.LinearLayoutManager? = null


    private val presenter = FeedPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            this,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )
        adapter = FeedRecViewAdapter(eventList, this, eventItemOnClick)

        events_recycler_view.adapter = adapter
        events_recycler_view.layoutManager = layoutManager

        presenter.onCreate()
        change_city_button.setOnClickListener {
            onChangeCityClick()
        }
        swipe_to_refresh.setOnRefreshListener { presenter.onUpdate() }
        swipe_to_refresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent ,R.color.colorPrimaryDark)

    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    fun initSelectedCity(city: City) {
        selectedCity = city
        change_city_button.text = city.name
    }

    fun showEvents(eventList: List<Event>) {
        //this.eventList = eventList as MutableList<Event>
        if(swipe_to_refresh.isRefreshing){
            swipe_to_refresh.isRefreshing = false
        }
        this.eventList.clear()
        this.eventList.addAll(eventList)

        adapter!!.notifyDataSetChanged()

        progress_bar.visibility = View.GONE
        events_recycler_view.visibility = View.VISIBLE

    }

    fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        events_recycler_view.visibility = View.GONE
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

                if (city.slug != selectedCity!!.slug) {
                    presenter.onCitySelected(city)
                }

            }
        }
    }

    fun updateSelectedCity(city: City) {
        selectedCity = city
        change_city_button.text = city.name
    }
}
