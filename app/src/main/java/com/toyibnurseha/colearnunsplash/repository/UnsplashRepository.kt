package com.toyibnurseha.colearnunsplash.repository

import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponse
import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponseItem
import com.toyibnurseha.colearnunsplash.data.source.local.UnsplashLocalDataSource
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.data.source.remote.UnsplashRemoteDataSource
import com.toyibnurseha.colearnunsplash.data.source.remote.network.ApiResponse
import com.toyibnurseha.colearnunsplash.data.source.remote.response.ListImageResponse
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.utils.AppExecutors
import com.toyibnurseha.colearnunsplash.utils.DataMapper
import com.toyibnurseha.themoviedb.data.NetworkBoundResource
import com.toyibnurseha.themoviedb.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UnsplashRepository(
    private val remoteDataSource: UnsplashRemoteDataSource,
    private val localRemoteDataSource: UnsplashLocalDataSource,
//    private val appExecutors: AppExecutors
) : UnsplashDataSource {

    override fun getAllRandomPhotos(sort: String): Flow<Resource<List<UnsplashModel>>> =
        object : NetworkBoundResource<List<UnsplashModel>, List<UnsplashResponseItem>>() {
            override fun loadFromDB(): Flow<List<UnsplashModel>> {
                return localRemoteDataSource.getRandomPhotos(sort).map {
                    //map entities to domain
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<UnsplashModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UnsplashResponseItem>>> {
                return remoteDataSource.getRandomPhotos()
            }

            override suspend fun saveCallResult(data: List<UnsplashResponseItem>) {
                //map response to entities
                val movieList = DataMapper.mapUnsplashResponseToEntities(data)
                localRemoteDataSource.insertRandomPhotos(movieList)
            }

        }.asFlow()

}