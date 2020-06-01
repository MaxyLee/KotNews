package com.example.android.kotnews.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kotnews.data.News
import com.example.android.kotnews.databinding.ListItemNewsBinding

class NewsAdapter(val clickListener: NewsListener, val likeListener: LikeListener): ListAdapter<News, NewsAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, likeListener, item)
    }

    class ViewHolder private constructor(val binding: ListItemNewsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: NewsListener, likeListener: LikeListener, item: News) {
            binding.news = item
            binding.clickListener = clickListener
            binding.likeListener = likeListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemNewsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class NewsDiffCallback: DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.newsId == oldItem.newsId
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }

}

class NewsListener(val clickListener: (newsId: Long) -> Unit) {
    fun onClick(news: News) = clickListener(news.newsId)
}

class LikeListener(val clickListener: (newsId: Long) -> Unit) {
    fun onClick(news: News) = clickListener(news.newsId)
}