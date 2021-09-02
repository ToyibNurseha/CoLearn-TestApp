package com.toyibnurseha.colearnunsplash.ui

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.toyibnurseha.colearnunsplash.R
import com.toyibnurseha.colearnunsplash.data.source.Resource
import com.toyibnurseha.colearnunsplash.databinding.ActivityMainBinding
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.utils.SortHelper
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var sort = SortHelper.RANDOM
    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setList(sort)
    }

    private fun setList(sort: String) {
        viewModel.getMovies(sort).observe(this, photoObserver)
    }

    private val photoObserver = Observer<Resource<List<UnsplashModel>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    print("is loading")
                }
                is Resource.Success -> {
//                    setDataState(DataState.SUCCESS)
//                    moviesAdapter.setData(movies.data)
                    print(movies.data)
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