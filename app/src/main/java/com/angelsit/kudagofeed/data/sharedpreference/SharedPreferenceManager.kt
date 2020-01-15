package com.angelsit.kudagofeed.data.sharedpreference

import android.content.Context
import com.angelsit.kudagofeed.data.dto.City
import com.google.gson.Gson

class SharedPreferenceManager {
    companion object {

        private const val SELECTED_CITY = "SELECTED_CITY"

        private val defaultCity = City("msk", "Москва")

        private const val CITY_PREF = "CITY_PREF"

        fun getSelectedCity(context: Context): City {
            val mPrefs = context.getSharedPreferences(CITY_PREF, 0)
            var resultCity: City = defaultCity
            val gson = Gson()
            val json: String? = mPrefs.getString(SELECTED_CITY, "")
             if(!json.isNullOrBlank()){
                resultCity = gson.fromJson(json, City::class.java)
            }

            return resultCity
        }

        fun saveSelectedCity(context: Context, city: City) {
            val mPrefs = context.getSharedPreferences(CITY_PREF, 0)
            val editor = mPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(city)
            editor.putString(SELECTED_CITY, json)
            editor.apply()


        }
    }
}