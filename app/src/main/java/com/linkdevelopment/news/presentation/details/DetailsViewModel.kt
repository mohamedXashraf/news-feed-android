package com.linkdevelopment.news.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linkdevelopment.domain.usecase.GetNewsById
import com.linkdevelopment.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel constructor(private val getNewsById: GetNewsById) : ViewModel() {

    val news = MutableLiveData<News>()

    fun getNewsById(id: String) = CoroutineScope(Dispatchers.IO).launch {
        news.postValue(getNewsById.run(id))
    }
}