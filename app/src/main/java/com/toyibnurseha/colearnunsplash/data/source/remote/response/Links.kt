package com.toyibnurseha.colearnunsplash.data.source.remote.response

import androidx.room.Entity

data class Links(
    val download: String,
    val download_location: String,
    val html: String,
    val self: String
)