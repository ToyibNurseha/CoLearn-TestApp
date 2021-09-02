package com.toyibnurseha.colearnunsplash.di

import androidx.room.Room
import com.toyibnurseha.colearnunsplash.data.source.local.UnsplashLocalDataSource
import com.toyibnurseha.colearnunsplash.data.source.local.room.UnsplashDatabase
import com.toyibnurseha.colearnunsplash.data.source.remote.UnsplashRemoteDataSource
import com.toyibnurseha.colearnunsplash.data.source.remote.network.UnsplashApi
import com.toyibnurseha.colearnunsplash.repository.UnsplashDataSource
import com.toyibnurseha.colearnunsplash.repository.UnsplashRepository
import com.toyibnurseha.colearnunsplash.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

