package uz.abdullajon.learndaggervsmvvm.ui.news


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_news_item.*
import uz.abdullajon.learndaggervsmvvm.R
import uz.abdullajon.learndaggervsmvvm.data.model.NewsArticle
import uz.abdullajon.learndaggervsmvvm.utils.ItemDiffer


class MyNewsRecyclerViewAdapter(
   private val context: Context,
    private val onSelectedAction: (NewsArticle) -> Unit
) :
    ListAdapter<NewsArticle, MyNewsRecyclerViewAdapter.ViewHolder>(ItemDiffer()) {


    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.fragment_news_item, parent, false)
        return ViewHolder(view, onSelectedAction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    inner class ViewHolder(
        override val containerView: View,
        onSelectedAction: (NewsArticle) -> Unit
    ) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var selectedItem: NewsArticle? = null

        init {
            containerView.setOnClickListener { selectedItem?.let { it1 -> onSelectedAction(it1) } }
        }

        fun bindData(itemNews: NewsArticle?) {
            itemNews?.run {
                Glide.with(context)
                    .load(itemNews.urlToImage)
                    .into(news_image)
                tv_title.text = itemNews.description
                selectedItem = itemNews
            }
        }
    }
}
