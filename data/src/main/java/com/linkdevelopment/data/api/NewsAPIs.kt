package com.linkdevelopment.data.api

import com.linkdevelopment.data.base.Response
import com.linkdevelopment.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIs {

    private val key: String
        get() = "533af958594143758318137469b41ba9"

    @GET("articles")
    suspend fun getArticles(
        @Query("source") source: String,
        @Query("apiKey") apiKey: String = key
    ): Response<News>
}