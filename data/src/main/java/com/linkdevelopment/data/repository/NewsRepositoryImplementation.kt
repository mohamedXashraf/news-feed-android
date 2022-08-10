package com.linkdevelopment.data.repository

import com.google.gson.Gson
import com.linkdevelopment.data.api.NewsAPIs
import com.linkdevelopment.data.base.Error
import com.linkdevelopment.data.base.Repository
import com.linkdevelopment.domain.repository.NewsRepository
import com.linkdevelopment.model.News
import retrofit2.HttpException

class NewsRepositoryImplementation : Repository(), NewsRepository {

    private val api = retrofit().create(NewsAPIs::class.java)

    private var nextWebArticles = mutableListOf<News>()

    private var associatedPressArticles = mutableListOf<News>()

    override suspend fun getNextWebArticles(): MutableList<News> {
        try {
            val response = api.getArticles("the-next-web")
            if (response.status != null && response.status.equals("ok", ignoreCase = true)) {
                nextWebArticles = response.articles ?: mutableListOf()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            if (ex is HttpException) throw parseHttpError(ex)
        }
        return nextWebArticles
    }

    override suspend fun getAssociatedPressArticles(): MutableList<News> {
        try {
            val response = api.getArticles("associated-press")
            if (response.status != null && response.status.equals("ok", ignoreCase = true)) {
                associatedPressArticles = response.articles ?: mutableListOf()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            if (ex is HttpException) throw parseHttpError(ex)
        }
        return associatedPressArticles
    }

    override fun getCachedArticles(): MutableList<News> {
        val articles = mutableListOf<News>()
        articles.addAll(nextWebArticles)
        articles.addAll(associatedPressArticles)
        return articles
    }

    private fun parseHttpError(ex: HttpException): Exception {
        val errorBody = ex.response()?.errorBody()?.string()
        return if (!errorBody.isNullOrEmpty()) {
            val error = Gson().fromJson(errorBody, Error::class.java).message
            Exception(error)
        } else ex
    }
}