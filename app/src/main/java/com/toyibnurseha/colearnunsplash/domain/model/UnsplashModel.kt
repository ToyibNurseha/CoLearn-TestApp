package com.toyibnurseha.colearnunsplash.domain.model

import androidx.room.ColumnInfo
import com.toyibnurseha.colearnunsplash.data.source.remote.response.Links
import com.toyibnurseha.colearnunsplash.data.source.remote.response.Urls
import com.toyibnurseha.colearnunsplash.data.source.remote.response.User

data class UnsplashModel(
    @ColumnInfo(name = "description")
    val alt_description: String,
    val color: String,
    @ColumnInfo(name = "createdAt")
    val created_at: String,
    @ColumnInfo(name = "description")
    val description: String,
    val height: Int,
    val id: String,
    val likes: Int,
//    val links: Links,
    @ColumnInfo(name = "promotedAt")
    val promoted_at: String,
    @ColumnInfo(name = "updatedAt")
    val updated_at: String,
    val urls: Urls,
//    val user: User,
    val width: Int
)