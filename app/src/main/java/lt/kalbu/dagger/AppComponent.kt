package lt.kalbu.dagger

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import lt.kalbu.App
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,

        CustomViewModelFactoryModule::class,
        CustomViewModelModule::class,

        AppModule::class,
        RepoModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {
    fun inject(app: Application)
}