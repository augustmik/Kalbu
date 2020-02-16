package lt.autismus.dagger

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import lt.autismus.App
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {
    fun inject(app: Application)
}