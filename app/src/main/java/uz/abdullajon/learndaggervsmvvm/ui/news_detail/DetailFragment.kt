package uz.abdullajon.learndaggervsmvvm.ui.news_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_fragment.*
import uz.abdullajon.learndaggervsmvvm.R
import uz.abdullajon.learndaggervsmvvm.data.model.NewsArticle
import uz.abdullajon.learndaggervsmvvm.ui.base.BaseFragment
import javax.inject.Inject

class DetailFragment : BaseFragment() {


    companion object {
        private val detailFragment = DetailFragment()
        fun instance(newsArticle: NewsArticle): DetailFragment {
            val bundle = Bundle()
            bundle.putParcelable("item", newsArticle)
            detailFragment.arguments = bundle
            return detailFragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailViewModel
    override fun layoutRes(): Int = R.layout.detail_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        val item = arguments?.getParcelable<NewsArticle>("item")
        if (item != null) {
            loadData(item)
        } else {
            parentFragmentManager.popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadData(item: NewsArticle) {
        Glide.with(context!!)
            .load(item.urlToImage)
            .into(image_item)
        tv_date.text = "${item.publishedAt.substring(0,10)} ${item.publishedAt.substring(11,16)}"
        tv_title_item.text = item.title
        tv_content.text = item.content
    }
}
