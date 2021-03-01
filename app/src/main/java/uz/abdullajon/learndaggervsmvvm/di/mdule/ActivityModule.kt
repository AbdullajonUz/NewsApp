package uz.abdullajon.learndaggervsmvvm.di.mdule

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.abdullajon.learndaggervsmvvm.ui.main.MainActivity
import uz.abdullajon.learndaggervsmvvm.ui.splash.SplashActivity


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMainActivity():MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

}