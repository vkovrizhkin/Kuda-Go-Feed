package com.angelsit.kudagofeed.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.data.dto.event.Date
import com.angelsit.kudagofeed.data.dto.eventdetails.EventDetails
import com.angelsit.kudagofeed.presenter.EventDetailsPresenter
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.info_block.*

class EventDetailsActivity : AppCompatActivity() {

    companion object {
        const val EVENT_ID_EXTRA = "EVENT_ID_EXTRA"
    }

    private val photosPagerAdapter = PhotosViewPagerAdapter()

    private val presenter = EventDetailsPresenter(this)

    var eventId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        photos_view_pager.adapter = photosPagerAdapter
        pager_indicator.setViewPager(photos_view_pager)

        back_button.setOnClickListener { finish() }

        eventId = intent.getStringExtra(EVENT_ID_EXTRA)

        swipe_to_refresh.setOnRefreshListener { presenter.onUpdate(eventId!!) }
        swipe_to_refresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorAccent,
            R.color.colorPrimaryDark
        )


    }

    override fun onResume() {
        super.onResume()
        if (!eventId.isNullOrBlank()) {
            presenter.onResume(eventId!!)
        }


    }

    fun showDetails(eventDetails: EventDetails) {

        title_text_view.text = eventDetails.title
        desc_text_view.text = eventDetails.description
        short_desc_text_view.text = eventDetails.description
        photosPagerAdapter.setPhotos(eventDetails.images.map { item -> item.image })

        if (swipe_to_refresh.isRefreshing) {
            swipe_to_refresh.isRefreshing = false
        }

        if (!eventDetails.price.isNullOrBlank()) {
            price_text_view.text = eventDetails.price
        } else {
            price_line.visibility = View.GONE
        }

        eventDetails.place?.let {
            if (it.address.isNotBlank()) {
                place_text_view.text = eventDetails.place.address
            } else {
                address_line.visibility = View.GONE
            }
        }

        if (eventDetails.dates.isEmpty()) {
            dates_text_view.text =
                Date.getDisplayDates(eventDetails.dates[0].start, eventDetails.dates.last().end)
        } else {
            dates_line.visibility = View.GONE
        }

        progress_bar.visibility = View.GONE
        content.visibility = View.VISIBLE

    }

    fun showLoading() {
        content.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }
}
