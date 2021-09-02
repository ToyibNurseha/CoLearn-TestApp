package com.toyibnurseha.colearnunsplash.domain.usecase

import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.repository.UnsplashRepository
import com.toyibnurseha.themoviedb.data.Resource
import kotlinx.coroutines.flow.Flow

class UnsplashAppInteractor(private val repo: UnsplashRepository) : UnsplashUseCase {
    override fun getRandomPhotos(sort: String): Flow<Resource<List<UnsplashModel>>>  = repo.getAllRandomPhotos(sort)
}