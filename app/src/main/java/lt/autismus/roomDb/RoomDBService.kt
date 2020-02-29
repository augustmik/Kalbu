package lt.autismus.roomDb

import android.content.Context
import androidx.room.Room
import lt.autismus.roomDb.dbObjects.CardDB

class RoomDBService (context: Context) {
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "nba_info_db"
    ).build()

    suspend fun addAllCards() : List<CardDB>{
        // TODO get images from file and generate card objects
        val cards = listOf<CardDB>()
        db.cardsDao().insertAll(cards)
        return db.cardsDao().getAll()
    }
    suspend fun loadAllCards() : List<CardDB>{
        return db.cardsDao().getAll()
    }
}