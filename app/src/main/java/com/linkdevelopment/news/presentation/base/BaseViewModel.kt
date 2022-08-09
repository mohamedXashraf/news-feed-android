package com.linkdevelopment.news.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<STATE, INTENT> : ViewModel()
{
    var state = MutableLiveData<STATE>()

    var intent = MutableLiveData<INTENT>()

    private var observer: Observer<INTENT> = Observer { }

    fun observeIntents(observer: Observer<INTENT>)
    {
        this.observer = observer
        intent.observeForever(observer)
    }

    fun clear()
    {
        super.onCleared()
        state = MutableLiveData()
        intent.removeObserver(observer)
        intent = MutableLiveData()
    }
}