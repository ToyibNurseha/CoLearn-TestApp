package com.toyibnurseha.colearnunsplash.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.toyibnurseha.colearnunsplash.data.source.local.converter.Converters
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel

@Entity(tableName = "searchEntity")
class SearchEntity(
    @PrimaryKey
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<UnsplashEntity>?
)