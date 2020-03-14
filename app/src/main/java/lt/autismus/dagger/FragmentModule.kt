package lt.autismus.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.autismus.frontScreen.cards.CardsFragment
import lt.autismus.frontScreen.categories.CategoryFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector()
    abstract fun contributeCategoriesFragment(): CategoryFragment

    @ContributesAndroidInjector()
    abstract fun contributeCardsFragment(): CardsFragment
}