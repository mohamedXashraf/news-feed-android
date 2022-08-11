package com.linkdevelopment.news.presentation.home

sealed class HomeViewIntent {
    object GetAllArticles : HomeViewIntent()
    class FilterArticles(val query: String) : HomeViewIntent()
}