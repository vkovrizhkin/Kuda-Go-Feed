package com.angelsit.kudagofeed.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.event.Date
import com.angelsit.kudagofeed.model.event.Event
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.event_list_item.view.*
import kotlinx.android.synthetic.main.info_block.view.*

class FeedRecViewAdapter(
    private val eventList: List<Event>,
    private val context: Context,
    private val callback: (Event) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<FeedRecViewAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedRecViewAdapter.EventViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.event_list_item, p0, false) as View
        return EventViewHolder(layout)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: FeedRecViewAdapter.EventViewHolder, index: Int) {
        val event = eventList[index]

        holder.container.setOnClickListener { callback(event) }
        holder.titleTextView.text = event.title
        holder.descTextView.text = event.description

        if (!event.price.isNullOrBlank()) {
            holder.priceTextView.text = event.price
        } else {
            holder.priceLine.visibility = View.GONE
        }

        if (event.place != null && event.place.address.isNotBlank()) {
            holder.placeTextView.text = event.place.address
        } else {
            holder.addressLine.visibility = View.GONE
        }

        if (event.dates != null && event.dates.isNotEmpty()) {
            holder.datesTextView.text = Date.getDisplayDates(event.dates[0].start, event.dates.last().end)
        } else {
            holder.datesLine.visibility = View.GONE
        }


        if (event.images != null && event.images.isNotEmpty()) {
            Glide.with(context).load(event.images[0].image).centerCrop().into(holder.imageView)
        }


    }

    class EventViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val container = view
        val imageView = view.event_image_view!!
        val titleTextView = view.title_text_view!!
        val descTextView = view.description_text_view!!
        val placeTextView = view.place_text_view!!
        val priceTextView = view.price_text_view!!
        val datesTextView = view.dates_text_view!!

        val priceLine = view.price_line
        val datesLine = view.dates_line
        val addressLine = view.address_line

    }


}