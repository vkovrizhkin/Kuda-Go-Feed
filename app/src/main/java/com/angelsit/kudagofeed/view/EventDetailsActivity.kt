package com.angelsit.kudagofeed.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.EventDetails
import com.angelsit.kudagofeed.presenter.EventDetailsPresenter
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {

    private val photosPagerAdapter = PhotosViewPagerAdapter()
    private val presenter = EventDetailsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume("12345")

    }

    fun showDetails(eventDetails: EventDetails){
        photos_view_pager.adapter = photosPagerAdapter
        photosPagerAdapter.setPhotos(eventDetails.photos)
        progress_bar.visibility = View.GONE

    }
}
