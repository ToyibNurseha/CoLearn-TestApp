package com.toyibnurseha.colearnunsplash.data.source.local

import com.toyibnurseha.colearnunsplash.data.source.local.entity.SearchEntity
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.data.source.local.room.UnsplashDao
import com.toyibnurseha.colearnunsplash.utils.SortHelper
import kotlinx.coroutines.flow.Flow

class UnsplashLocalDataSource(private val unsplashDao: UnsplashDao) {
    fun getRandomPhotos(sort: String): Flow<List<UnsplashEntity>> {
        val query = SortHelper.getSorted(sort)
        return unsplashDao.getRandomPhotos(query)
    }

    fun getSearch(sort: String): Flow<SearchEntity> {
        val query = SortHelper.getSortedSearch(sort)
        return unsplashDao.getSearch(query)
    }

    suspend fun insertRandomPhotos(movies: List<UnsplashEntity>) = unsplashDao.insertRandomPhotos(movies)

    suspend fun insertSearchPhotos(searchPhoto: SearchEntity) = unsplashDao.insertSearchPhotos(searchPhoto)
}