package com.toyibnurseha.colearnunsplash.ui.search

import android.content.Intent
import android.os.Bundle
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.toyibnurseha.colearnunsplash.data.source.Resource
import com.toyibnurseha.colearnunsplash.databinding.ActivitySearchBinding
import com.toyibnurseha.colearnunsplash.domain.model.SearchModel
import com.toyibnurseha.colearnunsplash.interfaces.FilterListener
import com.toyibnurseha.colearnunsplash.ui.FilterDialogFragment
import com.toyibnurseha.colearnunsplash.ui.adapter.SearchAdapter
import com.toyibnurseha.colearnunsplash.ui.photoviewer.PhotoViewerActivity
import com.toyibnurseha.colearnunsplash.ui.viewModel.SearchViewModel
import com.toyibnurseha.colearnunsplash.utils.Constant
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), FilterListener {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapterPhoto: SearchAdapter
    private val viewModel: SearchViewModel by viewModel()

    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    var searchPage = 1

    private var filterColor: String? = null
    private var filterOrientation: String? = null

    companion object{
        const val SEARCH_QUERY = "search_query"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterPhoto = SearchAdapter()
        initData()
        setupRecycler()

        binding.btnFilter.setOnClickListener {
            val fm : FragmentManager = supportFragmentManager
            val fragment : FilterDialogFragment = FilterDialogFragment.newInstance(filterColor, filterOrientation)
            fragment.show(fm, "tag")
        }
    }

    private fun setupRecycler() {
        binding.rvPhotosSearch.adapter = adapterPhoto
        binding.rvPhotosSearch.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvPhotosSearch.addOnScrollListener(this@SearchActivity.scrollListener)

        adapterPhoto.setOnItemClickListener {
            startActivity(Intent(this@SearchActivity, PhotoViewerActivity::class.java).apply {
                putExtra(PhotoViewerActivity.PHOTO_URL, it?.urls?.full)
                putExtra(PhotoViewerActivity.TITLE_PHOTO, it?.description)
            })
        }
    }

    private fun initData() {
        val query : String? = intent.getStringExtra(SEARCH_QUERY)
//        val query : String? = "dog"
        binding.etSearch.setText(query)
        viewModel.getSearch(query.toString(), page = searchPage).observe(this, searchObserver)
    }

    private val searchObserver = Observer<Resource<SearchModel>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Success -> {
                    searchPage++
                    isLoading = false
//                    adapterPhoto.setData(movies.data?.results)
                    adapterPhoto.differ.submitList(movies.data?.results)
                    if(movies.data?.totalPages != null) {
                        val totalPage = movies.data.totalPages / Constant.TOTAL_QUERY_PAGE + 2
                        isLastPage = searchPage == totalPage
                        if(isLastPage) {
                            binding.rvPhotosSearch.setPadding(0,0,0,0)
                        }
                    }
                }
                is Resource.Error -> {
                    print("error")
                }
                else -> {
                    print("else")
                }
            }
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            var firstVisibleItems: IntArray? = null
            var pastVisibleItems = 0
            firstVisibleItems = layoutManager.findFirstVisibleItemPositions(firstVisibleItems)
            if(firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                pastVisibleItems = firstVisibleItems[0]
            }

            val isNotLoadingAndLastPage = !isLoading && !isLastPage
            val isAtLastItem = pastVisibleItems + visibleItemCount >= totalItemCount
            val isNotAtBeginning = pastVisibleItems >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20
            val shouldPaginate =
                isNotLoadingAndLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                initData()
                isScrolling = false
            }
        }
    }

    override fun finishFilter(color: String?, orientation: String?) {
        filterColor = color
        filterOrientation = orientation
        viewModel.getSearch(binding.etSearch.text.toString(), colorSort = color, orientation = orientation, page = searchPage).observe(this, searchObserver)
    }
}