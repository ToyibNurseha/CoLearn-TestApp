package com.toyibnurseha.colearnunsplash.di

import androidx.room.Room
import com.toyibnurseha.colearnunsplash.data.source.local.UnsplashLocalDataSource
import com.toyibnurseha.colearnunsplash.data.source.local.room.UnsplashDatabase
import com.toyibnurseha.colearnunsplash.data.source.remote.UnsplashRemoteDataSource
import com.toyibnurseha.colearnunsplash.data.source.remote.network.UnsplashApi
import com.toyibnurseha.colearnunsplash.domain.usecase.UnsplashAppInteractor
import com.toyibnurseha.colearnunsplash.domain.usecase.UnsplashUseCase
import com.toyibnurseha.colearnunsplash.repository.UnsplashDataSource
import com.toyibnurseha.colearnunsplash.repository.UnsplashRepository
import com.toyibnurseha.colearnunsplash.ui.MainViewModel
import com.toyibnurseha.colearnunsplash.utils.AppExecutors
import com.toyibnurseha.colearnunsplash.utils.Constant.BASE_URL
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
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
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
    single{ UnsplashRepository(get(), get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    factory<UnsplashUseCase> { UnsplashAppInteractor(get()) }
    viewModel { MainViewModel(get()) }
}