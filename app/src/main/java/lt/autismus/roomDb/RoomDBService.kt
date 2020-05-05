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

    suspend fun addAllCards(cards: List<CardDB>, category: String): List<CardDB> {
        db.cardsDao().insertAll(cards)
        return db.cardsDao().getAllCategoryCards(category)
    }

    suspend fun addAllCards(cards: List<CardDB>) {
        db.cardsDao().insertAll(cards)
    }

    suspend fun loadAllCards(): List<CardDB> {
        return db.cardsDao().getAll()
    }

    suspend fun loadSelectedCategoryCards(categoryName: String): List<CardDB> {
        return db.cardsDao().getAllCategoryCards(categoryName)
    }

    suspend fun loadAllCategories(): List<CategoriesDB> {
        return db.cardsDao().getAllCategories()
    }

    suspend fun addAllCategories(categories: List<CategoriesDB>): List<CategoriesDB> {
        db.cardsDao().insertAllCategories(categories)
        return db.cardsDao().getAllCategories()
    }

    suspend fun deleteCard(cardId: Int, categoryName: String): List<CardDB> {
        db.cardsDao().deleteCard(cardId)
        return db.cardsDao().getAllCategoryCards(categoryName)
    }

    suspend fun deleteCategory(categoryName: String): List<CategoriesDB> {
        db.cardsDao().deleteCategory(categoryName)
        return db.cardsDao().getAllCategories()
    }
}