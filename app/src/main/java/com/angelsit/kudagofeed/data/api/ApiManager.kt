package com.angelsit.kudagofeed.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    var apiService: ApiService? = null

    fun getService(): ApiService {
        if (apiService==null){
            val retrofit = Retrofit.Builder()
                .baseUrl("https://kudago.com/public-api/v1.4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }
}