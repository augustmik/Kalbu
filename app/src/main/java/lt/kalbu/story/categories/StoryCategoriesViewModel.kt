package lt.kalbu.story.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.kalbu.repository.CardsRepo
import lt.kalbu.singleUnits.SingleCategory
import javax.inject.Inject

class StoryCategoriesViewModel @Inject constructor(
    val repository : CardsRepo
): ViewModel() {

    val categoriesLive : LiveData<List<SingleCategory>> = repository.getCategories()

    fun updateCategories(){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCategories()
        }
    }
}