package uz.abdullajon.learndaggervsmvvm.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import uz.abdullajon.learndaggervsmvvm.App
import uz.abdullajon.learndaggervsmvvm.di.mdule.ActivityModule
import uz.abdullajon.learndaggervsmvvm.di.mdule.AppModule
import uz.abdullajon.learndaggervsmvvm.di.mdule.NetworkModule
import uz.abdullajon.learndaggervsmvvm.di.mdule.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        NetworkModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityModule::class]
)
interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}