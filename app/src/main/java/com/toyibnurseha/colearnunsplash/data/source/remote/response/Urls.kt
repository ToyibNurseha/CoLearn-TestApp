package com.toyibnurseha.colearnunsplash.data.source.remote.response

import androidx.room.Entity

@Entity(tableName = "urls")
data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)