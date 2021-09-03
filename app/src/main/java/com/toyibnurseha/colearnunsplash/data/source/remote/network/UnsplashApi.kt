package com.toyibnurseha.colearnunsplash.data.source.remote.network

import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponse
import com.toyibnurseha.colearnunsplash.data.source.remote.response.ListImageResponse
import com.toyibnurseha.colearnunsplash.data.source.remote.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getRandomPhotos(
        @Query("client_id") apiKey: String,
    ): UnsplashResponse

    @GET("search/photos")
    suspend fun getSearch(
        @Query("client_id") apiKey: String,
        @Query("query") query: String,
        @Query("per_page") perPage:Int,
        @Query("page") page:Int,
        @Query("order_by") orderBy: String?,
        @Query("orientation") orientation: String?,
    ) : SearchResponse

}