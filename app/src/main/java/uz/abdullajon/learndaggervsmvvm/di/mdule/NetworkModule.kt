package uz.abdullajon.learndaggervsmvvm.di.mdule

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.abdullajon.learndaggervsmvvm.api.RequestInterceptor
import uz.abdullajon.learndaggervsmvvm.api.service.NewsService
import uz.abdullajon.learndaggervsmvvm.data.BASE_URL
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {
    @JvmStatic
    @Provides
    @Singleton
    @Named("cached")
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(RequestInterceptor())
            .addNetworkInterceptor(StethoInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(@Named("cached") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }


}