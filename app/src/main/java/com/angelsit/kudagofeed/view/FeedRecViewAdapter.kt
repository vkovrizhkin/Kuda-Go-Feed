package com.angelsit.kudagofeed.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.data.dto.event.Date
import com.angelsit.kudagofeed.data.dto.event.Event
import com.angelsit.kudagofeed.repo.EventsRepo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.event_list_item.view.*
import kotlinx.android.synthetic.main.info_block.view.*

class FeedRecViewAdapter(
    private val eventList: List<EventsRepo.EventPreviewEntity>,
    private val context: Context,
    private val callback: (EventsRepo.EventPreviewEntity) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<FeedRecViewAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedRecViewAdapter.EventViewHolder {
        val layout =
            LayoutInflater.from(context).inflate(R.layout.event_list_item, p0, false) as View
        return EventViewHolder(layout)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: FeedRecViewAdapter.EventViewHolder, index: Int) {
        val event = eventList[index]

        holder.container.setOnClickListener { callback(event) }
        holder.titleTextView.text = event.title
        holder.descTextView.text = event.description

        Glide.with(context).load(event.avatar).centerCrop().into(holder.imageView)


    }

    class EventViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val container = view
        val imageView = view.event_image_view!!
        val titleTextView = view.title_text_view!!
        val descTextView = view.description_text_view!!

    }


}