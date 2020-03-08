package lt.autismus.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.autismus.frontScreen.CategoryFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector()
    abstract fun contributeCategoriesFragment(): CategoryFragment
}