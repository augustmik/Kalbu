package lt.autismus.frontScreen.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.autismus.repository.CardsRepo
import lt.autismus.singleUnits.SingleCard
import lt.autismus.singleUnits.SingleCategory
import javax.inject.Inject

class CardsViewModel @Inject constructor(
    val repository : CardsRepo
): ViewModel() {

    val cardsLive : LiveData<List<SingleCard>> = repository.getCardsLive()

    fun updateCards(){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCards()
        }
    }
}