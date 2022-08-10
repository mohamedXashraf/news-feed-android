package com.linkdevelopment.domain.usecase

import com.linkdevelopment.domain.repository.NewsRepository
import com.linkdevelopment.model.News
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetAllArticles constructor(private val repository: NewsRepository) {

    suspend fun run(): MutableList<News> {
        val articles = mutableListOf<News>()
        coroutineScope {
            awaitAll(
                async { articles.addAll(repository.getNextWebArticles()) },
                async { articles.addAll(repository.getAssociatedPressArticles()) }
            )
        }
        return articles
    }
}