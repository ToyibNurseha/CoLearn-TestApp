package com.toyibnurseha.colearnunsplash.data.source.local

import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.data.source.local.room.UnsplashDao
import com.toyibnurseha.colearnunsplash.utils.SortHelper
import kotlinx.coroutines.flow.Flow

class UnsplashLocalDataSource(private val unsplashDao: UnsplashDao) {
    fun getRandomPhotos(sort: String): Flow<List<UnsplashEntity>> {
        val query = SortHelper.getSortedQueryMovies(sort)
        return unsplashDao.getRandomPhotos(query)
    }

    suspend fun insertRandomPhotos(movies: List<UnsplashEntity>) = unsplashDao.insertRandomPhotos(movies)
}