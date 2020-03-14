package lt.autismus.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import lt.autismus.frontScreen.MainActivity
import lt.autismus.util.PictureCoder
import javax.inject.Named

@Module
class CardMakerModule {
    @Provides
    fun providePictureCoder(@Named("mainActivityProvider") context: Context): PictureCoder = PictureCoder(context)

    @Module
    companion object {
        @Named("mainActivityProvider")
        @Provides
        @JvmStatic
        fun provideMainActivityContext(activity: MainActivity): Context = activity
    }
}