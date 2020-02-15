package com.nextbest.weatherappandroid.screen.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment
import com.nextbest.weatherappandroid.utils.observe
import com.nextbest.weatherappandroid.utils.setVisibility
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseViewModelFragment<SearchViewModel>() {

    override fun getViewModelClass() = SearchViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.showLoader.observe(this) {
            loaderView.setVisibility(it)
        }
        viewModel.showError.observe(this) {
            errorView.setVisibility(it)
        }
        viewModel.showNoData.observe(this) {
            noDataLayout.setVisibility(it)
        }
        viewModel.showSearch.observe(this) {
            searchInfoLayout.setVisibility(it)
        }
        viewModel.showList.observe(this) {
            recyclerView.setVisibility(it)
        }
    }

}
