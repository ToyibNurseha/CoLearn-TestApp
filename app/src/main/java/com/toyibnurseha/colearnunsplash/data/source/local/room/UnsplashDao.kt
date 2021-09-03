package com.toyibnurseha.colearnunsplash.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.toyibnurseha.colearnunsplash.data.source.local.entity.SearchEntity
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.data.source.remote.response.search.SearchResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface UnsplashDao {
    @RawQuery(observedEntities = [UnsplashEntity::class])
    fun getRandomPhotos(query: SupportSQLiteQuery): Flow<List<UnsplashEntity>>

    @RawQuery(observedEntities = [SearchEntity::class])
    fun getSearch(query: SupportSQLiteQuery): Flow<SearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRandomPhotos(movies: List<UnsplashEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchPhotos(search: SearchEntity)
}