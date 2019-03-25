package com.angelsit.kudagofeed.model

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

object MockData {

    fun getLocations(context: Context): List<City> {
        val jsonString: String?
        try {
            val inputStream = context.assets.open("mock_data/cities.json")
            val size = inputStream.available()

            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            jsonString = String(buffer, Charsets.UTF_8)

            val jsonResponse = JSONObject(jsonString)


            return parseCitiesFromJsonArray(jsonResponse.getJSONArray("cities"))
        } catch (ex: Exception) {
            return emptyList()
        }
    }

    private fun parseCitiesFromJsonArray(jsonArray: JSONArray): List<City> {
        val cityList = mutableListOf<City>()
        for (i in 0..(jsonArray.length() - 1)) {
            val name = jsonArray.getJSONObject(i).getString("name")
            val slug = jsonArray.getJSONObject(i).getString("slug")
            cityList.add(i, City(slug, name))
        }

        return cityList
    }

    fun getEvents(context: Context): List<Event> {
        val jsonString: String?
        try {
            val inputStream = context.assets.open("mock_data/events.json")
            val size = inputStream.available()

            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            jsonString = String(buffer, Charsets.UTF_8)

            val jsonResponse = JSONObject(jsonString)


            return parseEventsFromJsonArray(jsonResponse.getJSONArray("results"))
        } catch (ex: Exception) {
            return emptyList()
        }
    }

    private fun parseEventsFromJsonArray(jsonArray: JSONArray): List<Event> {
        val eventList = mutableListOf<Event>()
        for (i in 0..(jsonArray.length() - 1)) {
            var place = ""
            if(!jsonArray.getJSONObject(i).isNull("place")){
                place = jsonArray.getJSONObject(i).getJSONObject("place")?.getString("address")!!
            }

            val title = jsonArray.getJSONObject(i).getString("title")
            val description = jsonArray.getJSONObject(i).getString("description")
            val price = jsonArray.getJSONObject(i).getString("price")

            val imageArray = jsonArray.getJSONObject(i).getJSONArray("images")
            val imageUrl = if (imageArray.length() > 0) imageArray.getJSONObject(0).getString("image") else ""

            eventList.add(
                i, Event(
                    title, description, imageUrl, price, place, 0, 0
                )
            )

        }

        return eventList
    }

}