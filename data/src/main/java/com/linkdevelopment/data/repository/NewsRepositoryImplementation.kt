package com.linkdevelopment.data.repository

import com.linkdevelopment.data.api.NewsAPIs
import com.linkdevelopment.data.base.Repository
import com.linkdevelopment.domain.repository.NewsRepository
import com.linkdevelopment.model.News

class NewsRepositoryImplementation : Repository(), NewsRepository {

    private val api = retrofit().create(NewsAPIs::class.java)

    private var nextWebArticles = mutableListOf<News>()

    private var associatedPressArticles = mutableListOf<News>()

    override suspend fun getNextWebArticles(): MutableList<News> {
        val response = api.getArticles("the-next-web")
        try {
            if (response.status != null && response.status.equals("ok", ignoreCase = true)) {
                nextWebArticles = response.articles ?: mutableListOf()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nextWebArticles
    }

    override suspend fun getAssociatedPressArticles(): MutableList<News> {
        val response = api.getArticles("associated-press")
        try {
            if (response.status != null && response.status.equals("ok", ignoreCase = true)) {
                associatedPressArticles = response.articles ?: mutableListOf()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return associatedPressArticles
    }

    override fun getCachedArticles(): MutableList<News> {
        val articles = mutableListOf<News>()
        articles.addAll(nextWebArticles)
        articles.addAll(associatedPressArticles)
        return articles
    }
}