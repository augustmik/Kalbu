package lt.kalbu.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lt.kalbu.roomDb.RoomDBService
import lt.kalbu.roomDb.dbObjects.*
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.singleUnits.SingleCategory
import javax.inject.Inject

class CardsRepo @Inject constructor(
    private val roomService : RoomDBService
) {
    private var _allCards : MutableLiveData<List<SingleCard>> = MutableLiveData()//roomService.loadAllCards().asListCard()
    private var _allCategories : MutableLiveData<List<SingleCategory>> = MutableLiveData()//roomService.loadAllCards().asListCard()

    fun getCardsLive() : LiveData<List<SingleCard>> = _allCards
    fun getCategories() : LiveData<List<SingleCategory>> = _allCategories

    fun resetCardsLive() = _allCards.postValue(listOf())

    suspend fun addAllToDB(cards : List<SingleCard>, selectedCategory: String){
        val dbItems = roomService.addAllCards(cards.asListCardDB(), selectedCategory)
        _allCards.postValue(dbItems.asListCard())
    }

    suspend fun updateCards(categoryName: String){
        _allCards.postValue(roomService.loadSelectedCategoryCards(categoryName).asListCard())
    }
    suspend fun addCategoryToDB(categories: List<SingleCategory>){
        val categoryToAdd = roomService.addAllCategories(categories.asListCategoriesDB())
        _allCategories.postValue(categoryToAdd.asListSingleCategory())
    }
    suspend fun updateCategories(){
        _allCategories.postValue(roomService.loadAllCategories().asListSingleCategory())
    }

    suspend fun deleteCard(cardId: Int, categoryName: String){
        _allCards.postValue(roomService.deleteCard(cardId, categoryName).asListCard())
    }

    suspend fun deleteCategory(categoryName: String){
        _allCategories.postValue(roomService.deleteCategory(categoryName).asListSingleCategory())
    }

    suspend fun firstLaunchLoader(context: Context){
        val loader = FirstLaunchLoader(context)
        addCategoryToDB(loader.getCat())
        roomService.addAllCards(loader.getCards().asListCardDB())
    }
}