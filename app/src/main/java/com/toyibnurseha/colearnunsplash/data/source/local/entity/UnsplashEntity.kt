package com.toyibnurseha.colearnunsplash.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toyibnurseha.colearnunsplash.data.source.remote.response.*

@Entity(tableName = "photoEntity")
data class UnsplashEntity(
    @ColumnInfo(name = "altDescription")
    val alt_description: String,
    val color: String,
    @ColumnInfo(name = "createdAt")
    val created_at: String,
    @ColumnInfo(name = "description")
    val description: String,
    val height: Int,
    @PrimaryKey
    val id: String,
    val likes: Int,
//    val links: Links,
    @ColumnInfo(name = "promotedAt")
    val promoted_at: String,
    @ColumnInfo(name = "updatedAt")
    val updated_at: String,
//    val urls: Urls,
//    val user: User,
    val width: Int
)