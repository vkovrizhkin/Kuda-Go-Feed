package com.angelsit.kudagofeed.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelsit.kudagofeed.R
import com.angelsit.kudagofeed.data.dto.City
import kotlinx.android.synthetic.main.city_list_item.view.*

class CityRecViewAdapter    (
    private val cityList: List<City>,
    val context: Context,
    val callback: (City) -> Unit,
    private val selectedCity: City
) : androidx.recyclerview.widget.RecyclerView.Adapter<CityRecViewAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CityViewHolder {
        val layout = LayoutInflater.from(p0.context).inflate(R.layout.city_list_item, p0, false) as View

        return CityViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, index: Int) {

        val city = cityList[index]

        holder.nameTextView.text = cityList[index].name
        holder.selectedIcon.visibility = if(city.slug == selectedCity.slug) View.VISIBLE else View.GONE
        holder.container.setOnClickListener { callback(city)}
    }

    class CityViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val container = view
        val nameTextView = view.city_name!!
        val selectedIcon = view.city_selected!!
    }

}