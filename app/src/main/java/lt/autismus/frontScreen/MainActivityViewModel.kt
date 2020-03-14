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
    fun updateCards(){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCards()
        }
    }
//    fun updateCategories(){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.updateCategories()
//        }
//    }
    fun putItemsToDB(images: List<String>, cards  : List<SingleCard>){
        viewModelScope.launch(Dispatchers.IO) {
            for (index in cards.indices){
                cards[index].image = images[index]
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