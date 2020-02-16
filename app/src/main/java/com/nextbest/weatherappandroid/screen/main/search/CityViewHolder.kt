package com.nextbest.weatherappandroid.screen.main.search

import android.graphics.Typeface
import android.text.style.StyleSpan
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.utils.setTextWithSpan
import com.nextbest.weatherappandroid.utils.setVisibility
import kotlinx.android.synthetic.main.cell_city.view.*

class CityViewHolder(itemView: View, listener: Listener) : RecyclerView.ViewHolder(itemView) {

    private var listPosition: Int = 0

    init {
        itemView.expandButton.setOnClickListener {
            listener.expandClicked(listPosition)
        }
    }

    fun bindData(cityViewModel: CityViewModel, position: Int) {
        listPosition = position
        itemView.apply {
            cityName.text = cityViewModel.location.title
            infoLayout.setVisibility(cityViewModel.expanded)
            expandButton.text =
                itemView.context.getString(if (cityViewModel.expanded) R.string.search_show_less else R.string.search_show_more)
            locationType.setTextWithSpan(
                context.getString(
                    R.string.search_location_type,
                    cityViewModel.location.location_type
                ),
                cityViewModel.location.location_type,
                StyleSpan(Typeface.BOLD)
            )
        }
    }

    interface Listener {
        fun expandClicked(position: Int)
    }
}