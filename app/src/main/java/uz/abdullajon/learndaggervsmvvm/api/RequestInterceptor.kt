package uz.abdullajon.learndaggervsmvvm.api

import okhttp3.Interceptor
import okhttp3.Response
import uz.abdullajon.learndaggervsmvvm.data.API_KEY
import javax.inject.Inject

class RequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val requestBuilder = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(requestBuilder)
    }
}