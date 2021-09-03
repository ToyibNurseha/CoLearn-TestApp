package com.toyibnurseha.colearnunsplash.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.toyibnurseha.colearnunsplash.data.source.local.converter.Converters
import com.toyibnurseha.colearnunsplash.data.source.local.entity.SearchEntity
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity

@Database(entities = [UnsplashEntity::class, SearchEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UnsplashDatabase : RoomDatabase(){
    abstract fun unsplashDao() : UnsplashDao
}