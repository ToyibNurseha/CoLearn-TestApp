package com.toyibnurseha.colearnunsplash.domain.usecase

import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.data.source.Resource
import com.toyibnurseha.colearnunsplash.domain.model.SearchModel
import kotlinx.coroutines.flow.Flow

interface UnsplashUseCase {
    fun getRandomPhotos(sort: String): Flow<Resource<List<UnsplashModel>>>
    fun getSearch(
        query: String,
        colorSort: String?,
        orientation: String?,
        page: Int,
    ): Flow<Resource<SearchModel>>
}