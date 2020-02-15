package com.nextbest.weatherappandroid.screen.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment

class SearchFragment : BaseViewModelFragment<SearchViewModel>() {

    override fun getViewModelClass() = SearchViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}
