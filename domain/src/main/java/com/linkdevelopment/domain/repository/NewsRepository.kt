package com.linkdevelopment.domain.repository

import com.linkdevelopment.model.News

interface NewsRepository {

    suspend fun getNextWebArticles(): MutableList<News>

    suspend fun getAssociatedPressArticles(): MutableList<News>

    fun getCachedArticles(): MutableList<News>
}