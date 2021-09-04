package com.toyibnurseha.colearnunsplash.domain.usecase

import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.repository.UnsplashRepository
import com.toyibnurseha.colearnunsplash.data.source.Resource
import com.toyibnurseha.colearnunsplash.domain.model.SearchModel
import kotlinx.coroutines.flow.Flow

class UnsplashAppInteractor(private val repo: UnsplashRepository) : UnsplashUseCase {
    override fun getRandomPhotos(sort: String): Flow<Resource<List<UnsplashModel>>>  = repo.getAllRandomPhotos(sort)
    override fun getSearch(
        query: String,
        colorSort: String?,
        orientation: String?,
        page: Int
    ): Flow<Resource<SearchModel>> = repo.getSearch(query, colorSort, orientation, page)
}