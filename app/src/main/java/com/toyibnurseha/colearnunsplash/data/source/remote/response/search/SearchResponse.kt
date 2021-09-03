package com.toyibnurseha.colearnunsplash.data.source.remote.response.search

import com.google.gson.annotations.SerializedName
import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponseItem
import com.toyibnurseha.colearnunsplash.data.source.remote.response.ListImageResponse
import com.toyibnurseha.colearnunsplash.data.source.remote.response.ListImageResponseItem

data class SearchResponse(
    val total: Int,
    @SerializedName("total_pages")
    val totalPages : Int,
    val results : List<UnsplashResponseItem>
)