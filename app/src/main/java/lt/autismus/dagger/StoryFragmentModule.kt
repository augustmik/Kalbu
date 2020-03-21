package lt.autismus.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.autismus.story.cards.StoryCardsFragment
import lt.autismus.story.categories.StoryCategoryFragment

@Module
abstract class StoryFragmentModule {

    @ContributesAndroidInjector()
    abstract fun contributeStoryCategoriesFragment(): StoryCategoryFragment

    @ContributesAndroidInjector()
    abstract fun contributeStoryCardsFragment(): StoryCardsFragment
}