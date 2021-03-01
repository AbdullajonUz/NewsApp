package uz.abdullajon.learndaggervsmvvm.di.mdule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.abdullajon.learndaggervsmvvm.di.ViewModelFactory
import uz.abdullajon.learndaggervsmvvm.di.keys.ViewModelKey
import uz.abdullajon.learndaggervsmvvm.ui.main.MainViewModel
import uz.abdullajon.learndaggervsmvvm.ui.news.NewsViewModel
import uz.abdullajon.learndaggervsmvvm.ui.news_detail.DetailViewModel
import uz.abdullajon.learndaggervsmvvm.ui.splash.SplashViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}
