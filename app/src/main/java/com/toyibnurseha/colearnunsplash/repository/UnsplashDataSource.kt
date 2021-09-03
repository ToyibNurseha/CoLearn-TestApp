package com.toyibnurseha.colearnunsplash.repository

import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.data.source.Resource
import com.toyibnurseha.colearnunsplash.data.source.remote.response.search.SearchResponse
import com.toyibnurseha.colearnunsplash.domain.model.SearchModel
import kotlinx.coroutines.flow.Flow

interface UnsplashDataSource {
    fun getAllRandomPhotos(sort: String): Flow<Resource<List<UnsplashModel>>>
    fun getSearch(query: String, orderBy: String, orientation: String, page: Int) : Flow<Resource<SearchModel>>
}