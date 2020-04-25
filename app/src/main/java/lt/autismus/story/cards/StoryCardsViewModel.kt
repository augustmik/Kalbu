package lt.autismus.story.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.autismus.repository.CardsRepo
import lt.autismus.singleUnits.SingleCard
import javax.inject.Inject

class StoryCardsViewModel @Inject constructor(
    private val repository : CardsRepo
): ViewModel() {

    val cardsLive : LiveData<List<SingleCard>> = repository.getCardsLive()

    fun updateCards(categoryName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCards(categoryName)
        }
    }

    fun resetCardsLive(){
        repository.resetCardsLive()
    }
}