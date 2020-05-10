package lt.kalbu

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import lt.kalbu.dagger.AppModule
import lt.kalbu.dagger.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this))
            .build()
    }
}
