package com.toyibnurseha.colearnunsplash.utils

import com.toyibnurseha.colearnunsplash.data.response.UnsplashResponseItem
import com.toyibnurseha.colearnunsplash.data.source.local.entity.SearchEntity
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.data.source.remote.response.search.SearchResponse
import com.toyibnurseha.colearnunsplash.domain.model.SearchModel
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel

object DataMapper {
    fun mapUnsplashResponseToEntities(input: List<UnsplashResponseItem>): List<UnsplashEntity> {
        val photoList = ArrayList<UnsplashEntity>()
        input.map {
            val movie = UnsplashEntity(
                it.alt_description,
                it.color,
                it.created_at,
                "",
                it.height,
                it.id,
                it.likes,
//                it.links,
                "",
                it.updated_at,
                it.urls,
//                null,
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
//                it.links,
                it.promoted_at,
                it.updated_at,
                it.urls,
//                null,
//                null,
                it.width,
            )
        }
    }

    fun mapUnsplashSearchResponseToEntities(input: SearchResponse): SearchEntity {
        val photosEntity = ArrayList<UnsplashEntity>()
        input.results.map {
            val photo = UnsplashEntity(
                "it.alt_description",
                it.color,
                it.created_at,
                "",
                it.height,
                it.id,
                it.likes,
//                it.links,
                "",
                it.updated_at,
                it.urls,
//                null,
                it.width,
            )
            photosEntity.add(photo)
        }
        return SearchEntity(
            input.total,
            input.totalPages,
            photosEntity
        )
    }

    fun mapEntitiesSearchToDomain(input: SearchEntity?): SearchModel {
        val photoList = ArrayList<UnsplashModel>()
        input?.results?.map {
            val photo = UnsplashModel(
                it.alt_description,
                it.color,
                it.created_at,
                "",
                it.height,
                it.id,
                it.likes,
//                it.links,
                "",
                it.updated_at,
                it.urls,
//                null,
                it.width,
            )
            photoList.add(photo)
        }
        input?.let{
            return SearchModel(
                input.total,
                input.totalPages,
                photoList
            )
        }
        return SearchModel(
            null,
            null,
            null,
        )
    }
}