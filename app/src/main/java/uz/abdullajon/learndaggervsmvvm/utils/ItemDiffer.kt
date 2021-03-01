package uz.abdullajon.learndaggervsmvvm.utils

import androidx.recyclerview.widget.DiffUtil
import uz.abdullajon.learndaggervsmvvm.data.model.NewsArticle

class ItemDiffer : DiffUtil.ItemCallback<NewsArticle>() {
    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean = true
}
