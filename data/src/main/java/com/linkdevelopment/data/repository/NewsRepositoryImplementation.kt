package com.linkdevelopment.data.repository

import com.linkdevelopment.data.api.NewsAPIs
import com.linkdevelopment.data.base.Repository
import com.linkdevelopment.domain.repository.NewsRepository

class NewsRepositoryImplementation : Repository(), NewsRepository {

    private val api = retrofit().create(NewsAPIs::class.java)


}