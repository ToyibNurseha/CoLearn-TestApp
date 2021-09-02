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

val databaseModule = module {
    factory { get<UnsplashDatabase>().unsplashDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("codext".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            UnsplashDatabase::class.java, "unsplash.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(UnsplashApi::class.java)
    }
}

val repositoryModule = module {
    single { UnsplashLocalDataSource(get()) }
    single { UnsplashRemoteDataSource(get()) }
    factory { AppExecutors() }
    single<UnsplashDataSource> { UnsplashRepository(get(), get()) }
}