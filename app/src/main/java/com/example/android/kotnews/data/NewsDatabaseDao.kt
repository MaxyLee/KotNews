package com.example.android.kotnews.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDatabaseDao {
    @Insert
    fun insert(news: News)

    @Update
    fun update(news: News)

    @Query("select * from news_table where newsId = :key")
    fun get(key: Long): News?

    @Query("delete from news_table")
    fun clear()

    @Query("select * from news_table order by newsId desc")
    fun getAllNews(): LiveData<List<News>>

    @Query("select * from news_table where newsId = :key")
    fun getNewsWithId(key: Long): LiveData<News>
}