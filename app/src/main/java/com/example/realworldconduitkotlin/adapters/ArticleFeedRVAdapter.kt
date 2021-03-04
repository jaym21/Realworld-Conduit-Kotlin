package com.example.realworldconduitkotlin.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.api.models.entities.Article
import com.example.realworldconduitkotlin.R

class ArticleFeedRVAdapter: androidx.recyclerview.widget.ListAdapter<Article, ArticleFeedRVAdapter.ArticleViewHolder>(
        object: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.toString() == newItem.toString()
    }

}
) {



    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.ivFeedAvatar)
        val author: TextView = itemView.findViewById(R.id.tvAuthor)
        val date: TextView = itemView.findViewById(R.id.tvArticleDate)
        val title: TextView = itemView.findViewById(R.id.tvArticleTitle)
        val body: TextView = itemView.findViewById(R.id.tvArticleBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_article_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.author.text = article.author.username
        holder.title.text = article.title
        holder.body.text = article.body
        holder.date.text = article.createdAt
    }

}