package com.toyibnurseha.colearnunsplash.utils

import android.graphics.Movie
import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponseItem
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel

object DataMapper {
    fun mapUnsplashResponseToEntities(input: List<UnsplashResponseItem>): List<UnsplashEntity> {
        val photoList = ArrayList<UnsplashEntity>()
        input.map {
            val movie = UnsplashEntity(
                it.alt_description,
                it.color,
                it.created_at,
                it.description,
                it.height,
                it.id,
                it.likes,
//                it.links,
                it.promoted_at,
                it.updated_at,
//                it.urls,
//                it.user,
                it.width,
            )
            photoList.add(movie)
        }
        return photoList
    }

    fun mapEntitiesToDomain(input: List<UnsplashEntity>): List<UnsplashModel> {
        return input.map {
            UnsplashModel(
                it.alt_description,
                it.color,
                it.created_at,
                it.description,
                it.height,
                it.id,
                it.likes,
//                null,
                it.promoted_at,
                it.updated_at,
//                null,
//                null,
                it.width,
            )
        }
    }
}