package com.angelsit.kudagofeed.repo

import android.content.Context
import android.net.Uri
import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.data.api.Api

object EventsRepo {

    data class EventPreviewEntity(
        val id: Int,
        val title: String,
        val description: String,
        val avatar: String
    )

    fun getEvents(citySlug: String, listener: MainContract.Presenter.GetEventsListener) {
        Api.getEvents(citySlug, listener)
    }

    fun getDataFromPersist(context: Context) {
        val cursor = context.contentResolver.query(
            Uri.parse("content://com.angelsit.kudagofeed.data.contentprovider.EventsProvider/events"),
            null,
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            val event = EventPreviewEntity(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("description")),
                cursor.getString(cursor.getColumnIndex("avatar"))
            )
            val e = event
        }
    }
}