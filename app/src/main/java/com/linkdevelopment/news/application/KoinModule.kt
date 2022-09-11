package com.linkdevelopment.news.application

import com.linkdevelopment.data.repository.NewsRepositoryImplementation
import com.linkdevelopment.domain.repository.NewsRepository
import com.linkdevelopment.domain.usecase.FilterArticles
import com.linkdevelopment.domain.usecase.GetAllArticles
import com.linkdevelopment.domain.usecase.GetNewsById
import com.linkdevelopment.news.presentation.details.DetailsViewModel
import com.linkdevelopment.news.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single<NewsRepository> { NewsRepositoryImplementation() }

    single { GetAllArticles(get()) }

    single { GetNewsById(get()) }

    single { FilterArticles(get()) }

    viewModel { HomeViewModel(get(), get()) }

    viewModel { DetailsViewModel(get()) }
}