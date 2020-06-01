package com.example.android.kotnews.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.kotnews.data.News
import com.example.android.kotnews.data.NewsDatabaseDao
import kotlinx.coroutines.*
import java.lang.Appendable

class HomeViewModel(
    val database: NewsDatabaseDao,
    application: Application): AndroidViewModel(application) {

    private val TAG = "HomeViewModel"

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val allnews = database.getAllNews()

    private val _navigateToNews = MutableLiveData<Long>()
    val navigateToNews: LiveData<Long>
        get() = _navigateToNews

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun insert(news: News) {
        withContext(Dispatchers.IO) {
            database.insert(news)
        }
    }

    private suspend fun update(newsId: Long) {
        withContext(Dispatchers.IO) {
            val news = database.get(newsId)
            news?.let {
                if(!it.viewed) {
                    it.viewed = true
                    database.update(it)
                }
            }
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            Log.i(TAG, "onClear called!")
        }
    }

    fun onInsert() {
        uiScope.launch {
            val news = News()
            insert(news)
            Log.i(TAG, "onInsert called! ${allnews.value?.size}")
        }
    }

    fun onNewsViewed(newsId: Long) {
        uiScope.launch {
            Log.i(TAG, "onNewsViewed called!")
            update(newsId)
        }
    }

    fun onNewsClicked(id: Long) {
        _navigateToNews.value = id
    }

    fun onNewsNavigated() {
        _navigateToNews.value = null
    }
}