package lt.autismus

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import lt.autismus.dagger.AppModule
import lt.autismus.dagger.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this))
            .build()
    }
}
