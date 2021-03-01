package uz.abdullajon.learndaggervsmvvm.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import uz.abdullajon.learndaggervsmvvm.data.model.AllNews

interface NewsService {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("category") category: String
    ): Single<AllNews>
}