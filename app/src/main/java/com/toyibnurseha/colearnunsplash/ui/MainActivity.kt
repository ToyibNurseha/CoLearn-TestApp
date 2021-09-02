package com.toyibnurseha.colearnunsplash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toyibnurseha.colearnunsplash.R
import com.toyibnurseha.colearnunsplash.databinding.ActivityMainBinding
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
    }
}