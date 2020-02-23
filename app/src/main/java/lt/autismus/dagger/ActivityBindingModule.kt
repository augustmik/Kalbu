package lt.autismus.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.autismus.frontScreen.MainActivity


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(/*modules = [FragmentsModule::class]*/)
    abstract fun contributeMainActivity(): MainActivity


}
