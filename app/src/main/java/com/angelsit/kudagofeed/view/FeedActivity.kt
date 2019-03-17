package com.angelsit.kudagofeed.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.angelsit.kudagofeed.R
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        change_city_button.setOnClickListener {
            onChangeCityClick()
        }
        change_city_button.text = "Москва"
    }

    //TODO  вернуть город ...
    private val onChangeCityClick = fun() {
        val intent = Intent(this, CityListActivity::class.java)
        startActivity(intent)
    }
}
