package com.linkdevelopment.data.api

import com.linkdevelopment.data.base.Response
import com.linkdevelopment.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIs {

    private val key: String
        get() = "91da53ae90924e6981690f1c3128ef89"

    @GET("articles")
    suspend fun getArticles(
        @Query("source") source: String,
        @Query("apiKey") apiKey: String = key
    ): Response<News>
}