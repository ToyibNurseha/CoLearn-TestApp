package com.toyibnurseha.colearnunsplash.di

import com.toyibnurseha.colearnunsplash.domain.usecase.UnsplashAppInteractor
import com.toyibnurseha.colearnunsplash.domain.usecase.UnsplashUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UnsplashUseCase> { UnsplashAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
//    viewModel { MoviesViewModel(get()) }
//    viewModel { TvShowsViewModel(get()) }
//    viewModel { DetailViewModel(get()) }
//    viewModel { SearchViewModel(get()) }
}