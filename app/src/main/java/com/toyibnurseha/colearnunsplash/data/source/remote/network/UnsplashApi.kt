package com.toyibnurseha.colearnunsplash.data.source.remote.network

import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponse
import com.toyibnurseha.colearnunsplash.data.source.remote.response.ListImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getRandomPhotos(
        @Query("client_id") apiKey: String,
    ): UnsplashResponse

}