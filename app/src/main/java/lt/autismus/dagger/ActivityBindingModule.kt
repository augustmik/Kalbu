package lt.autismus.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.autismus.frontScreen.MainActivity
import lt.autismus.settings.SettingsActivity
import lt.autismus.story.SelectionActivity
import lt.autismus.story.StoryActivity


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingsActivity(): SettingsActivity

    @ContributesAndroidInjector(modules = [StoryFragmentModule::class])
    abstract fun contributeSelectionActivity(): SelectionActivity

    @ContributesAndroidInjector
    abstract fun contributeStoryActivity(): StoryActivity
}
