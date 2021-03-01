package uz.abdullajon.learndaggervsmvvm.repository

import io.reactivex.Single
import uz.abdullajon.learndaggervsmvvm.data.model.AllNews

interface NewsRepository {
    fun loadNews(country: String, category: String): Single<AllNews>
}