package lt.autismus.dagger

import android.app.Application
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideSharedPrefsEditor(app: Application): SharedPreferences =
        app.getSharedPreferences("sharedPrefs", 0)
}