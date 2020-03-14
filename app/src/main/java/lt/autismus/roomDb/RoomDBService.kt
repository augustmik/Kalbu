package lt.autismus.roomDb

import android.content.Context
import androidx.room.Room
import lt.autismus.roomDb.dbObjects.CardDB
import lt.autismus.roomDb.dbObjects.CategoriesDB
import javax.inject.Singleton

@Singleton
class RoomDBService(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "autismus_db"
    )
        .fallbackToDestructiveMigration()
        .build()

    suspend fun addAllCards(cards: List<CardDB>): List<CardDB> {
        db.cardsDao().insertAll(cards)
        return db.cardsDao().getAll()
    }

    suspend fun loadAllCards(): List<CardDB> {
        return db.cardsDao().getAll()
    }

    suspend fun loadAllCategories(): List<CategoriesDB> {
        return db.cardsDao().getAllCategories()
    }

    suspend fun addAllCategories(categories: List<CategoriesDB>): List<CategoriesDB> {
        db.cardsDao().insertAllCategories(categories)
        return db.cardsDao().getAllCategories()
    }
}