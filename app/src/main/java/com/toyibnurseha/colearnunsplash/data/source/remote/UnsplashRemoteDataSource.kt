package com.toyibnurseha.colearnunsplash.data.source.remote

import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponse
import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponseItem
import com.toyibnurseha.colearnunsplash.data.source.remote.network.ApiResponse
import com.toyibnurseha.colearnunsplash.data.source.remote.network.UnsplashApi
import com.toyibnurseha.colearnunsplash.data.source.remote.response.ListImageResponse
import com.toyibnurseha.colearnunsplash.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UnsplashRemoteDataSource(private val unsplashApi: UnsplashApi) {

    suspend fun getRandomPhotos() : Flow<ApiResponse<List<UnsplashResponseItem>>> {
        return flow {
            try {
                val response = unsplashApi.getRandomPhotos(Constant.API_KEY)
                val photos = response
                if(photos.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}