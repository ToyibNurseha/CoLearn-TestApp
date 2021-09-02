package com.toyibnurseha.colearnunsplash.domain.usecase

import android.graphics.Movie
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.themoviedb.data.Resource
import kotlinx.coroutines.flow.Flow

interface UnsplashUseCase {
    fun getRandomPhotos(sort: String): Flow<Resource<List<UnsplashModel>>>
}