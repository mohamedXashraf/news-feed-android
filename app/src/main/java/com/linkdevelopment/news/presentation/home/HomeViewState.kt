package com.linkdevelopment.news.presentation.home

import com.linkdevelopment.model.News

sealed class HomeViewState {
    object ViewInitializationState : HomeViewState()
    class DataState(val news: MutableList<News>) : HomeViewState()
    class ErrorState(val error: Throwable) : HomeViewState()
    object LoadingState : HomeViewState()
}