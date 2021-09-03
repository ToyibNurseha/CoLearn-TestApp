package com.toyibnurseha.colearnunsplash.repository

import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponseItem
import com.toyibnurseha.colearnunsplash.data.source.local.UnsplashLocalDataSource
import com.toyibnurseha.colearnunsplash.data.source.remote.UnsplashRemoteDataSource
import com.toyibnurseha.colearnunsplash.data.source.remote.network.ApiResponse
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.utils.DataMapper
import com.toyibnurseha.colearnunsplash.data.source.NetworkBoundResource
import com.toyibnurseha.colearnunsplash.data.source.Resource
import com.toyibnurseha.colearnunsplash.data.source.remote.response.search.SearchResponse
import com.toyibnurseha.colearnunsplash.domain.model.SearchModel
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

    override fun getSearch(
        query: String,
        orderBy: String,
        orientation: String,
        page: Int
    ): Flow<Resource<SearchModel>>  =
        object : NetworkBoundResource<SearchModel, SearchResponse>() {

            override fun shouldFetch(data: SearchModel?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<SearchResponse>> {
                return remoteDataSource.getSearch(query, orderBy, orientation, page)
            }

            override suspend fun saveCallResult(data: SearchResponse) {
                //map response to entities
                val movieList = DataMapper.mapUnsplashSearchResponseToEntities(data)
                localRemoteDataSource.insertSearchPhotos(movieList)
            }

            override fun loadFromDB(): Flow<SearchModel> {
                return localRemoteDataSource.getSearch(query).map {
                    //map entities to domain
                    DataMapper.mapEntitiesSearchToDomain(it)
                }
            }

        }.asFlow()


}