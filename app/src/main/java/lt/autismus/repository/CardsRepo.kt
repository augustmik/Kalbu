package lt.autismus.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lt.autismus.roomDb.RoomDBService
import lt.autismus.roomDb.dbObjects.asListCard
import lt.autismus.roomDb.dbObjects.asListCardDB
import lt.autismus.singleUnits.SingleCard
import javax.inject.Inject

class CardsRepo @Inject constructor(
    private val roomService : RoomDBService
) {
    private var _allCards : MutableLiveData<List<SingleCard>> = MutableLiveData()//roomService.loadAllCards().asListCard()

    fun getCardsLive() : LiveData<List<SingleCard>> = _allCards

    suspend fun addAllToDB(cards : List<SingleCard>){
        val dbItems = roomService.addAllCards(cards.asListCardDB())
        _allCards.postValue(dbItems.asListCard())
    }

    suspend fun updateCards(){
        _allCards.postValue(roomService.loadAllCards().asListCard())
    }
}