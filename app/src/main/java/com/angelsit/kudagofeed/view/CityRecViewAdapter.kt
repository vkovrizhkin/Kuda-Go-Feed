package com.angelsit.kudagofeed.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.angelsit.kudagofeed.model.City

class CityRecViewAdapter(
    private val cityList: List<City>,
    val context: Context,
    val callback: (City) -> Unit
) : RecyclerView.Adapter<CityRecViewAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CityViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(p0: CityViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}