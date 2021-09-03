package com.toyibnurseha.colearnunsplash.domain.model

import com.google.gson.annotations.SerializedName
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.data.source.remote.response.ListImageResponse

data class SearchModel(
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    val results: List<UnsplashModel>?
)