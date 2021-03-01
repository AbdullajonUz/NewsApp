package uz.abdullajon.learndaggervsmvvm.di.mdule

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.abdullajon.learndaggervsmvvm.data.model.NewsArticle
import uz.abdullajon.learndaggervsmvvm.ui.news.NewsListFragment
import uz.abdullajon.learndaggervsmvvm.ui.news_detail.DetailFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun bindNewsListFragment():NewsListFragment

    @ContributesAndroidInjector
    abstract fun bindDetailFragment():DetailFragment
}