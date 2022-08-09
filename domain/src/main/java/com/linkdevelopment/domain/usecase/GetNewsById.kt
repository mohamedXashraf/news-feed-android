package com.linkdevelopment.domain.usecase

import com.linkdevelopment.domain.repository.NewsRepository

class GetNewsById constructor(private val repository: NewsRepository) {

    fun run(id: String) = repository.getCachedArticles().find { it.id == id }
}