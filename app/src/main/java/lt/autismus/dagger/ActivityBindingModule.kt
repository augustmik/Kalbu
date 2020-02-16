package lt.autismus.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.autismus.MainActivity


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(/*modules = [FragmentsModule::class]*/)
    abstract fun contributeMainActivity(): MainActivity


}
