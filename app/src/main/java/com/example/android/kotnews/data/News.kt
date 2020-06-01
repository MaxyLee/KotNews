package com.example.android.kotnews.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(
    @PrimaryKey(autoGenerate = true)
    val newsId: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String = "No title",
    @ColumnInfo(name = "author")
    val author: String = " No author",
    @ColumnInfo(name = "text")
    val text: String = "No text",
    @ColumnInfo(name = "viewed")
    var viewed: Boolean = false,
    @ColumnInfo(name = "liked")
    var liked: Boolean = false) {
}