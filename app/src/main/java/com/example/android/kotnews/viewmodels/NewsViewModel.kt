package com.example.android.kotnews.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.android.kotnews.data.News
import com.example.android.kotnews.data.NewsDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class NewsViewModel(
    private val newsId: Long = 0L,
    val database: NewsDatabaseDao): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val news = MediatorLiveData<News>()

    fun getNews() = news

    init {
        news.addSource(database.getNewsWithId(newsId), news::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}