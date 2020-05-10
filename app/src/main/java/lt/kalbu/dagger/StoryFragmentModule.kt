package lt.kalbu.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.kalbu.story.cards.StoryCardsFragment
import lt.kalbu.story.categories.StoryCategoryFragment

@Module
abstract class StoryFragmentModule {

    @ContributesAndroidInjector()
    abstract fun contributeStoryCategoriesFragment(): StoryCategoryFragment

    @ContributesAndroidInjector()
    abstract fun contributeStoryCardsFragment(): StoryCardsFragment
}