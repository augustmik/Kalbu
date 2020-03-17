package lt.autismus.roomDb.dbObjects

import androidx.room.*

@Dao
interface CardDao {

    @Query("SELECT * FROM Cards")
    fun getAll(): List<CardDB>

    @Query("SELECT * FROM Cards WHERE cardCategory = (:category)")
    fun getAllCategoryCards(category: String): List<CardDB>

    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cards: List<CardDB>)

    @Query("SELECT * FROM Categories")
    fun getAllCategories(): List<CategoriesDB>

    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(categories: List<CategoriesDB>)

    @Query("DELETE FROM Cards WHERE idCard = :cardId")
    fun deleteCard(cardId: Int)

    @Query("DELETE FROM Categories WHERE categoryName = :name")
    fun deleteCategory(name: String)
}