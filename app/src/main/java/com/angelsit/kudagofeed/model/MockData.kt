package com.angelsit.kudagofeed.model

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

object MockData {

    fun getLocations(context: Context) : List<City>?{
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
        } catch (ex: Exception){
            return null
        }
    }

    private fun parseCitiesFromJsonArray(jsonArray: JSONArray): List<City>{
        val cityList = mutableListOf<City>()
        for (i in 0..(jsonArray.length()-1)){
            val name = jsonArray.getJSONObject(i).getString("name")
            val slug = jsonArray.getJSONObject(i).getString("slug")
            cityList.add(i, City(slug, name))
        }

        return cityList
    }
}