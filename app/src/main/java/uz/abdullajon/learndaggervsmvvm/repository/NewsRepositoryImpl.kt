package uz.abdullajon.learndaggervsmvvm.repository

import io.reactivex.Single
import uz.abdullajon.learndaggervsmvvm.api.service.NewsService
import uz.abdullajon.learndaggervsmvvm.data.model.AllNews
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    NewsRepository {


    override fun loadNews(country: String, category: String): Single<AllNews> {
        return newsService.getNews(country, category)
    }


}