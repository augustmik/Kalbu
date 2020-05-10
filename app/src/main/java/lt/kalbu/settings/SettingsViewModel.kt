package lt.kalbu.settings

import androidx.lifecycle.ViewModel
import lt.kalbu.repository.CardsRepo
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
