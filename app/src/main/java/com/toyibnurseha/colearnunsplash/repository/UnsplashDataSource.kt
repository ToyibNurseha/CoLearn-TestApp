package com.toyibnurseha.colearnunsplash.repository

import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.themoviedb.data.Resource
import kotlinx.coroutines.flow.Flow

interface UnsplashDataSource {
    fun getAllRandomPhotos(sort: String): Flow<Resource<List<UnsplashModel>>>
}