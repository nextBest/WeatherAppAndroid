package com.nextbest.weatherappandroid.screen.main.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.data.model.Location
import kotlinx.android.synthetic.main.cell_city.view.*

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(location: Location) {
        itemView.cityName.text = location.title
    }
}