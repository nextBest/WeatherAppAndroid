package com.nextbest.weatherappandroid.screen.main.search

import android.graphics.Typeface
import android.text.style.StyleSpan
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.utils.setTextWithSpan
import com.nextbest.weatherappandroid.utils.setVisibility
import kotlinx.android.synthetic.main.cell_city.view.*

class CityViewHolder(itemView: View, listener: Listener) : RecyclerView.ViewHolder(itemView) {

    private var listPosition: Int = 0
    private var cityCell: CityCell? = null

    init {
        itemView.expandButton.setOnClickListener {
            listener.expandClicked(listPosition)
        }
        itemView.showPlace.setOnClickListener {
            cityCell?.let { cityViewModel ->
                listener.showPlaceOnMap(cityViewModel.location)
            }
        }
        itemView.setOnClickListener {
            cityCell?.let { cityViewModel ->
                listener.cellClicked(cityViewModel.location)
            }
        }
    }

    fun bindData(cityCell: CityCell, position: Int) {
        listPosition = position
        this.cityCell = cityCell
        itemView.apply {
            cityName.text = cityCell.location.title
            infoLayout.setVisibility(cityCell.expanded)
            expandButton.text =
                context.getString(if (cityCell.expanded) R.string.search_show_less else R.string.search_show_more)
            locationType.setTextWithSpan(
                context.getString(
                    R.string.search_location_type,
                    cityCell.location.location_type
                ),
                cityCell.location.location_type,
                StyleSpan(Typeface.BOLD)
            )
        }
    }

    interface Listener {
        fun expandClicked(position: Int)
        fun showPlaceOnMap(location: Location)
        fun cellClicked(location: Location)
    }
}