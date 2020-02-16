package com.nextbest.weatherappandroid.screen.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment
import com.nextbest.weatherappandroid.utils.observe
import com.nextbest.weatherappandroid.utils.setVisibility
import com.nextbest.weatherappandroid.views.ErrorView
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseViewModelFragment<SearchViewModel>() {

    override fun getViewModelClass() = SearchViewModel::class.java
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupErrorView()
        setupHideSearchViewOnScrollList()
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
        viewModel.errorType.observe(this) {
            errorView.setErrorType(it)
        }
        viewModel.cityList.observe(this) {
            cityAdapter.setData(it)
        }
    }

    private fun setupRecyclerView() {
        cityAdapter = CityAdapter()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = cityAdapter
        }
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchCity(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun setupErrorView() {
        errorView.setReloadListener(object : ErrorView.Listener {
            override fun reload() {
                viewModel.searchCity(searchView.query.toString())
            }
        })
    }

    private fun setupHideSearchViewOnScrollList() {
        val searchViewHeight = resources.getDimension(R.dimen.searchLayoutHeight)
        var scrollPosition = 0
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                searchView.clearFocus()
                scrollPosition -= dy
                if (scrollPosition > -searchViewHeight) {
                    searchLayout.translationY = scrollPosition.toFloat()
                } else if (searchLayout.y > -searchViewHeight) {
                    searchLayout.translationY = -searchViewHeight
                }
            }
        })
    }

}
