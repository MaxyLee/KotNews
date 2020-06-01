package com.example.android.kotnews.viewmodels

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.kotnews.R
import com.example.android.kotnews.data.News

@BindingAdapter("newsTitle")
fun TextView.setNewsTitle(item: News?) {
    item?.let {
        text = it.title
    }
}

@BindingAdapter("newsAuthor")
fun TextView.setNewsAuthor(item: News?) {
    item?.let {
        text = it.author
    }
}

@BindingAdapter("newsText")
fun TextView.setNewsText(item: News?) {
    item?.let {
        text = it.text
    }
}

@BindingAdapter("newsColor")
fun TextView.setNewsColor(item: News?) {
    item?.let {
        setTextColor(if(it.viewed) {
            resources.getColor(R.color.gray)
        } else {
            resources.getColor(R.color.black)
        })
//        setTextColor(resources.getColor(R.color.colorAccent))
    }
}

@BindingAdapter("liked")
fun ImageView.setLiked(item: News?) {
    item?.let {
        background = if(it.liked) {
            resources.getDrawable(R.drawable.ic_liked)
        } else {
            resources.getDrawable(R.drawable.ic_like)
        }
    }
}