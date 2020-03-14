package lt.autismus.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.autismus.frontScreen.MainActivity
import lt.autismus.settings.SettingsActivity


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class, CardMakerModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingsActivity(): SettingsActivity
}
