package lt.kalbu.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.kalbu.frontScreen.MainActivity
import lt.kalbu.settings.SettingsActivity
import lt.kalbu.story.SelectionActivity
import lt.kalbu.story.StoryActivity


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
