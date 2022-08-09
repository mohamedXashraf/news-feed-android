package com.linkdevelopment.domain.usecase

import com.linkdevelopment.domain.repository.NewsRepository
import com.linkdevelopment.model.News
import io.reactivex.rxjava3.core.Observable

class GetAllArticles constructor(private val repository: NewsRepository) {

    suspend fun run(): MutableList<News> {
        val articles = mutableListOf<News>()
        Observable.merge(
            Observable.just(repository.getNextWebArticles()),
            Observable.just(repository.getAssociatedPressArticles())
        ).subscribe { articles.addAll(it) }
        return articles
    }
}