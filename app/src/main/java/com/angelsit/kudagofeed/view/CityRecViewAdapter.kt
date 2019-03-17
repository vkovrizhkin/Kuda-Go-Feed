package com.angelsit.kudagofeed.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.model.City
import kotlinx.android.synthetic.main.city_list_item.view.*

class CityRecViewAdapter(
    private val cityList: List<City>,
    val context: Context,
    val callback: (City) -> Unit,
    private val selectedCity: City
) : RecyclerView.Adapter<CityRecViewAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CityViewHolder {
        val layout = LayoutInflater.from(p0.context).inflate(R.layout.city_list_item, p0, false) as View

        return CityViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(p0: CityViewHolder, p1: Int) {

        val city = cityList[p1]

        p0.nameTextView.text = cityList[p1].name
        p0.selectedIcon.visibility = if(city.slug == selectedCity.slug) View.VISIBLE else View.GONE
        p0.container.setOnClickListener { callback(city)}
    }

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container = view
        val nameTextView = view.city_name!!
        val selectedIcon = view.city_selected!!
    }

}