package lt.autismus.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import lt.autismus.frontScreen.MainActivity
import lt.autismus.util.PictureCoder
import javax.inject.Singleton

@Module
class CardMakerModule {
    @Provides
    fun providePictureCoder(context: Context): PictureCoder = PictureCoder(context)

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideMainActivityContext(activity: MainActivity): Context = activity
    }
}