package lt.autismus.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.autismus.repository.CardsRepo
import lt.autismus.singleUnits.SingleCard
import javax.inject.Inject


class SettingsViewModel @Inject constructor(
    private val repository: CardsRepo
): ViewModel() {
//    fun putItemsToDB(images: List<String>, cards  : List<SingleCard>){
//        viewModelScope.launch(Dispatchers.IO) {
//            for (index in cards.indices){
//                cards[index].image = images[index]
//            }
//            repository.addAllToDB(cards)
//        }
//    }
}
