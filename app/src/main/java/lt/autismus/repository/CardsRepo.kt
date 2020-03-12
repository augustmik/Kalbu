package lt.autismus.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lt.autismus.roomDb.RoomDBService
import lt.autismus.roomDb.dbObjects.*
import lt.autismus.singleUnits.SingleCard
import lt.autismus.singleUnits.SingleCategory
import javax.inject.Inject

class CardsRepo @Inject constructor(
    private val roomService : RoomDBService
) {
    private var _allCards : MutableLiveData<List<SingleCard>> = MutableLiveData()//roomService.loadAllCards().asListCard()
    private var _allCategories : MutableLiveData<List<SingleCategory>> = MutableLiveData()//roomService.loadAllCards().asListCard()

    fun getCardsLive() : LiveData<List<SingleCard>> = _allCards
    fun getCategories() : LiveData<List<SingleCategory>> = _allCategories

    suspend fun addAllToDB(cards : List<SingleCard>){
        val dbItems = roomService.addAllCards(cards.asListCardDB())
        _allCards.postValue(dbItems.asListCard())
    }

    suspend fun updateCards(){
        _allCards.postValue(roomService.loadAllCards().asListCard())
    }
    suspend fun addCategoryToDB(category: SingleCategory){
        val categoryToAdd = roomService.addCategory(category.asCategoriesDB())
        _allCategories.postValue(categoryToAdd.asListSingleCategory())
    }
    suspend fun updateCategories(){
        _allCategories.postValue(roomService.loadAllCategories().asListSingleCategory())
    }
}