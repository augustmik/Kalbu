package lt.autismus.repository

import lt.autismus.roomDb.RoomDBService
import lt.autismus.roomDb.dbObjects.asListCard
import lt.autismus.roomDb.dbObjects.asListCardDB
import lt.autismus.singleUnits.SingleCard
import javax.inject.Inject

class CardsRepo @Inject constructor(
    private val roomService : RoomDBService
) {
    var allCards : List<SingleCard> = listOf()

    suspend fun addAllToDB(cards : List<SingleCard>){
        val dbItems = roomService.addAllCards(cards.asListCardDB())
        allCards = dbItems.asListCard()
    }
}