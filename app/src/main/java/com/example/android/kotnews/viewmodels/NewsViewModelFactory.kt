package com.example.android.kotnews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.kotnews.data.NewsDatabaseDao

class NewsViewModelFactory(
    private val newsKey: Long,
    private val dataSource: NewsDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}