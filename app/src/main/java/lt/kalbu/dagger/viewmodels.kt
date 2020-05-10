package lt.kalbu.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import lt.kalbu.frontScreen.categories.CategoriesViewModel
import lt.kalbu.frontScreen.MainActivityViewModel
import lt.kalbu.frontScreen.cards.CardsViewModel
import lt.kalbu.settings.SettingsViewModel
import lt.kalbu.story.cards.StoryCardsViewModel
import lt.kalbu.story.categories.StoryCategoriesViewModel
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory @Inject constructor(private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?: viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

@Module
abstract class CustomViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(customViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActViewModel(customViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    abstract fun bindCategoriesViewModel(customViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CardsViewModel::class)
    abstract fun bindCardsViewModel(customViewModel: CardsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StoryCategoriesViewModel::class)
    abstract fun bindStoryCategoriesViewModel(customViewModel: StoryCategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StoryCardsViewModel::class)
    abstract fun bindStoryCardsViewModel(customViewModel: StoryCardsViewModel): ViewModel
}

@Module
abstract class CustomViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: CustomViewModelFactory): ViewModelProvider.Factory
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)