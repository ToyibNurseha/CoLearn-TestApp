package com.toyibnurseha.colearnunsplash.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.toyibnurseha.colearnunsplash.data.source.Resource
import com.toyibnurseha.colearnunsplash.databinding.ActivityMainBinding
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.ui.adapter.PhotoAdapter
import com.toyibnurseha.colearnunsplash.ui.photoviewer.PhotoViewerActivity
import com.toyibnurseha.colearnunsplash.ui.search.SearchActivity
import com.toyibnurseha.colearnunsplash.ui.viewModel.MainViewModel
import com.toyibnurseha.colearnunsplash.utils.SortHelper
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var sort = SortHelper.RANDOM
    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterPhoto: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterPhoto = PhotoAdapter()
        inputListener()
        setupRecycler()
        setList(sort)
    }

    private fun inputListener() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val text = binding.etSearch.text?.trim()
                val intent = Intent(this, SearchActivity::class.java).putExtra(
                    SearchActivity.SEARCH_QUERY,
                    text.toString()
                )
                startActivity(intent)
            }
            false
        }
    }

    private fun setupRecycler() {
        binding.rvPhotos.adapter = adapterPhoto
        binding.rvPhotos.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapterPhoto.setOnItemClickListener {
            startActivity(Intent(this, PhotoViewerActivity::class.java).apply {
                putExtra(PhotoViewerActivity.PHOTO_URL, it?.urls?.full)
                putExtra(PhotoViewerActivity.TITLE_PHOTO, it?.description)
            })
        }
    }

    private fun setList(sort: String) {
        viewModel.getRandomPhoto(sort).observe(this, photoObserver)
    }

    private val photoObserver = Observer<Resource<List<UnsplashModel>>> { photos ->
        if (photos != null) {
            when (photos) {
                is Resource.Loading -> {
                    print("is loading")
                }
                is Resource.Success -> {
                    adapterPhoto.setData(photos.data)
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
}