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

    fun getInfoDetail(): EventDetails{
        return EventDetails(
            listOf(
                "https://imgclf.112.ua/original/2018/09/03/357636.jpg?timestamp=1535976988",
                "https://imgclf.112.ua/original/2018/09/03/357636.jpg?timestamp=1535976988",
                "https://imgclf.112.ua/original/2018/09/03/357636.jpg?timestamp=1535976988"
            ),
            "Концерт Укупника",
            "ДК Кирова",
            "50 - 60 руб.",
            "лучший концерт в мире",
            "В Украине концертов, на которые есть большой спрос, становится все больше. Аншлаги в Домах культуры, больших концертных залах, Дворцах спорта и даже на стадионах собирают как звезды мирового масштаба, так и отечественные артисты. Все чаще спрос на концерт больше, чем количество билетов. Это вызывает волны мошенничеств, связанных с продажей билетов.\n" +
                    "\n" +
                    "На днях на НСК \"Олимпийский\" состоялся концерт американской группы Imagine Dragons. Все билеты были проданы еще до концерта. Более 50 тысяч зрителей насладились невероятным шоу, которое будут вспоминать много лет. К сожалению, не все, кто заплатил за билеты, смогли попасть на стадион. Они будут помнить этот день не меньше. Как это произошло?\n" +
                    "\n" +
                    "Во-первых, имела место беспрецедентная афера. Наибольшее количество пострадавших купили билеты через сайт e-ticket.in.ua. Во-вторых, аферисты через доски объявлений, аккаунты в Instagram и других соцсетях продавали копии билетов, приобретенных у официальных билетных операторов. В-третьих, аферисты продавали \"билеты\" на поддельных бланках операторов.",
            "15:00 - 15:20, 5 октября"

        )
    }

}