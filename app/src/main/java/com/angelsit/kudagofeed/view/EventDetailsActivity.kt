package com.angelsit.kudagofeed.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.eventdetails.EventDetails
import com.angelsit.kudagofeed.presenter.EventDetailsPresenter
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {

    private val photosPagerAdapter = PhotosViewPagerAdapter()
    private val presenter = EventDetailsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        photos_view_pager.adapter = photosPagerAdapter
        pager_indicator.setViewPager(photos_view_pager)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume("60843")

    }

    fun showDetails(eventDetails: EventDetails) {
        title_text_view.text = eventDetails.title
        desc_text_view.text = eventDetails.description
        short_desc_text_view.text = eventDetails.description
        photosPagerAdapter.setPhotos(eventDetails.images.map { item -> item.image })


        progress_bar.visibility = View.GONE
        content.visibility = View.VISIBLE

    }
    fun showLoading(){
        content.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }
}
