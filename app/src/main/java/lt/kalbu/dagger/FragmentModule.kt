package lt.kalbu.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.kalbu.frontScreen.cards.CardsFragment
import lt.kalbu.frontScreen.categories.CategoryFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector()
    abstract fun contributeCategoriesFragment(): CategoryFragment

    @ContributesAndroidInjector()
    abstract fun contributeCardsFragment(): CardsFragment
}