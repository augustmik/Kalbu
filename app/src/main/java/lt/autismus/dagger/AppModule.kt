package lt.autismus.dagger

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import lt.autismus.roomDb.RoomDBService
import lt.autismus.util.PictureCoder
import lt.autismus.util.TimedViewHider
import javax.inject.Singleton


@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideSharedPrefsEditor(app: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)

    @Provides
    @Singleton
    fun provideRoomDBService(): RoomDBService = RoomDBService(app)

    @Provides
    @Singleton
    fun providePictureCoder(): PictureCoder = PictureCoder(app)

    @Provides
    @Singleton
    fun provideViewHiderTimer() : TimedViewHider = TimedViewHider()
}