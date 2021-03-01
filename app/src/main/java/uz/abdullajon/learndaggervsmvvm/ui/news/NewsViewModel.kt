package uz.abdullajon.learndaggervsmvvm.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import uz.abdullajon.learndaggervsmvvm.data.model.AllNews
import uz.abdullajon.learndaggervsmvvm.data.model.QueryModel
import uz.abdullajon.learndaggervsmvvm.repository.NewsRepositoryImpl
import javax.inject.Inject

class NewsViewModel @Inject constructor(private var newsRepository: NewsRepositoryImpl) :
    ViewModel() {


    private val cd = CompositeDisposable()

    private val newsModelMutableData = MutableLiveData<QueryModel>()
    val newsModelLiveData: LiveData<AllNews>


    fun postNewsQuery(queryModel: QueryModel) = newsModelMutableData.postValue(queryModel)

    init {
        this.newsModelLiveData = newsModelMutableData.switchMap { queryModel ->
            loadData(queryModel)
        }
    }

    private fun loadData(queryModel: QueryModel): LiveData<AllNews> {
        val liveData = MutableLiveData<AllNews>()
        cd.add(
            newsRepository
                .loadNews(queryModel.counrty, queryModel.category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        liveData.postValue(it)
                    },
                    {
                        it.printStackTrace()
                        liveData.postValue(null)
                    }
                )
        )
        return liveData
    }
}