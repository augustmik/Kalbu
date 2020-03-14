package lt.autismus.frontScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.autismus.repository.CardsRepo
import lt.autismus.singleUnits.SingleCard
import lt.autismus.singleUnits.SingleCategory
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
            repository.addAllToDB(cards)
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
}