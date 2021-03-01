package uz.abdullajon.learndaggervsmvvm.ui.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.news_list_fragment.*
import uz.abdullajon.learndaggervsmvvm.R
import uz.abdullajon.learndaggervsmvvm.data.model.NewsArticle
import uz.abdullajon.learndaggervsmvvm.data.model.QueryModel
import uz.abdullajon.learndaggervsmvvm.ui.base.BaseFragment
import javax.inject.Inject

class NewsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var onClickListiner: OnClickListener? = null

    lateinit var newsAdapter: MyNewsRecyclerViewAdapter

    override fun layoutRes(): Int = R.layout.news_list_fragment


    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (context is OnClickListener) {
            onClickListiner = context as OnClickListener
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_news.layoutManager = LinearLayoutManager(context)
        newsAdapter = MyNewsRecyclerViewAdapter(context!!, this@NewsListFragment::onSelectionAction)
        rv_news.adapter = newsAdapter
        newsViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[NewsViewModel::class.java]
        loadData()
    }

    private fun onSelectionAction(item: NewsArticle) {
        onClickListiner?.onClick(item)
    }

    private fun loadData() {
        newsViewModel.postNewsQuery(QueryModel("us", "technology"))
        newsViewModel.newsModelLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                newsAdapter.submitList(it.articles)
            } else {
                Toast.makeText(context, "Check your internet Connection!", Toast.LENGTH_SHORT)
                    .show()
            }
            progress_bar.visibility = View.GONE
            rv_news.visibility = View.VISIBLE
        })
    }
}

interface OnClickListener {
    fun onClick(item: NewsArticle)
}
