package com.angelsit.kudagofeed.repo

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import com.angelsit.kudagofeed.App
import com.angelsit.kudagofeed.data.api.Api
import com.angelsit.kudagofeed.data.contentprovider.EventsProvider
import com.angelsit.kudagofeed.data.sharedpreference.SharedPreferenceManager
import io.reactivex.Completable
import io.reactivex.Single

object EventsRepo {

    data class EventPreviewEntity(
        val id: Int,
        val title: String,
        val description: String,
        val avatar: String
    )

    fun getEvents(citySlug: String): Single<List<EventPreviewEntity>> {

        if (SharedPreferenceManager.getDataWasFetched()) {
            return Single.fromCallable {
                getDataFromPersist(App.getAppContext())
            }
        } else {
            return Api.getEvents(citySlug).map {
                it.results.map(EventMapper::previewFromDto)
            }.doAfterSuccess {
                Completable.fromCallable {
                    for (event in it) {
                        saveEvent(event)
                    }
                }.subscribe(
                    {
                        SharedPreferenceManager.setDataWasFetched()
                    },
                    {

                        //todo handle errors
                    })
            }
        }
    }

    private fun saveEvent(event: EventPreviewEntity) {
        val values = ContentValues()
        values.put(EventsProvider.id, event.id)
        values.put(EventsProvider.title, event.title)
        values.put(EventsProvider.description, event.description)
        values.put(EventsProvider.avatar, event.avatar)
        App.getAppContext().contentResolver.insert(EventsProvider.CONTENT_URI, values)
    }

    private fun getDataFromPersist(context: Context): List<EventPreviewEntity> {
        val cursor = context.contentResolver.query(
            Uri.parse("content://com.angelsit.kudagofeed.data.contentprovider.EventsProvider/events"),
            null,
            null,
            null,
            null
        )
        val result = mutableListOf<EventPreviewEntity>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {

                val event = EventPreviewEntity(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("description")),
                    cursor.getString(cursor.getColumnIndex("avatar"))
                )
                result.add(event)
            }
        }

        return result.toList()
    }
}