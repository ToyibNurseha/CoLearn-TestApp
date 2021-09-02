package com.toyibnurseha.colearnunsplash.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toyibnurseha.colearnunsplash.data.source.local.entity.UnsplashEntity

@Database(entities = [UnsplashEntity::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase(){
    abstract fun unsplashDao() : UnsplashDao
}