package com.toyibnurseha.colearnunsplash.data.source.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity
import com.toyibnurseha.colearnunsplash.data.source.remote.response.Urls

class Converters {

    private var countryLangs: List<UnsplashEntity>? = null

    @TypeConverter
    fun fromUrlsToString(value: Urls): String {
        val gson = Gson()
        val type = object : TypeToken<Urls>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringUrl(value: String): Urls {
        val gson = Gson()
        val type = object : TypeToken<Urls>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun unsplashToString(value: List<UnsplashEntity>): String {
     val type = object : TypeToken<List<UnsplashEntity>>() {}.type
     return Gson().toJson(value, type)
    }

    @TypeConverter
    fun stringToUnsplash(string: String): List<UnsplashEntity> {
        val type = object : TypeToken<List<UnsplashEntity>>() {}.type
        return Gson().fromJson(string, type)
    }

}