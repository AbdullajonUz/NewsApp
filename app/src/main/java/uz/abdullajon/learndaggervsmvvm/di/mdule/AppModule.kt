package uz.abdullajon.learndaggervsmvvm.di.mdule

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import uz.abdullajon.learndaggervsmvvm.ui.main.MainActivity
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

}