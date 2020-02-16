package com.nextbest.weatherappandroid.screen.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Location

class CityAdapter(private val listener: Listener) :
    ListAdapter<CityViewModel, CityViewHolder>(DiffCallback),
    CityViewHolder.Listener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cell_city,
                parent,
                false
            ), this
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindData(getItem(position), position)
    }

    fun setData(data: List<Location>) {
        submitList(data.map {
            return@map CityViewModel(it)
        })
    }

    override fun expandClicked(position: Int) {
        val item = getItem(position)
        item.expanded = item.expanded.not()
        notifyItemChanged(position)
    }

    override fun showPlaceOnMap(location: Location) {
        listener.showPlaceOnMap(location)
    }

    override fun cellClicked(location: Location) {
        listener.cellClicked(location)
    }

    private object DiffCallback : DiffUtil.ItemCallback<CityViewModel>() {
        override fun areItemsTheSame(oldItem: CityViewModel, newItem: CityViewModel): Boolean {
            return oldItem.location.woeid == newItem.location.woeid
        }

        override fun areContentsTheSame(oldItem: CityViewModel, newItem: CityViewModel): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun showPlaceOnMap(location: Location)
        fun cellClicked(location: Location)
    }
}