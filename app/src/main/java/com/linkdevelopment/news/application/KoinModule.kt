package com.linkdevelopment.news.application

import com.linkdevelopment.data.repository.NewsRepositoryImplementation
import com.linkdevelopment.domain.repository.NewsRepository
import com.linkdevelopment.domain.usecase.GetAllArticles
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single<NewsRepository> { NewsRepositoryImplementation() }

    single { GetAllArticles(get()) }

    // viewModel { HomeViewModel(get()) }
}