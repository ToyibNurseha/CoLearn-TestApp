package com.toyibnurseha.colearnunsplash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.domain.usecase.UnsplashUseCase
import com.toyibnurseha.colearnunsplash.data.source.Resource

class MainViewModel(private val unsplashUseCase: UnsplashUseCase) : ViewModel() {
    fun getMovies(sort: String): LiveData<Resource<List<UnsplashModel>>> =
        unsplashUseCase.getRandomPhotos(sort).asLiveData()
}