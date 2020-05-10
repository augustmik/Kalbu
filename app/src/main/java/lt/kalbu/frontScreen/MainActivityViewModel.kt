package lt.kalbu.frontScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.kalbu.repository.CardsRepo
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.singleUnits.SingleCategory
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repository : CardsRepo
) : ViewModel() {
    fun putCardsToDB(images: List<String>, cards  : List<SingleCard>, selectedCategory: String){
        viewModelScope.launch(Dispatchers.IO) {
            for (index in cards.indices){
                cards[index].image = images[index]
                cards[index].category = selectedCategory
            }
            repository.addAllToDB(cards, selectedCategory)
        }
    }

    fun putCategoriesToDB(images: List<String>, cards  : List<SingleCategory>){
        viewModelScope.launch(Dispatchers.IO) {
            for (index in cards.indices){
                cards[index].image = images[index]
            }
            repository.addCategoryToDB(cards)
        }
    }

    fun fistTimeLaunchLoader(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            repository.firstLaunchLoader(context)
        }
    }
}