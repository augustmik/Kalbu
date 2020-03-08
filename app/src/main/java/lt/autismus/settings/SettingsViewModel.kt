package lt.autismus.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.autismus.roomDb.RoomDBService
import lt.autismus.roomDb.dbObjects.asListCardDB
import lt.autismus.singleUnits.SingleCard
import javax.inject.Inject


class SettingsViewModel @Inject constructor(
    private val roomService : RoomDBService
): ViewModel() {
    fun putItemsToDB(images: List<String>, cards  : List<SingleCard>){
        viewModelScope.launch(Dispatchers.IO) {
            for (index in cards.indices){
                cards[index].image = images[index]
            }
            val dbItems = roomService.addAllCards(cards.asListCardDB())
        }
    }
}
