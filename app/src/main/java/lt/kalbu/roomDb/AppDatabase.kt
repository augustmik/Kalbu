package lt.kalbu.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import lt.kalbu.roomDb.dbObjects.CardDB
import lt.kalbu.roomDb.dbObjects.CardDao
import lt.kalbu.roomDb.dbObjects.CategoriesDB

@Database(entities = [CardDB::class, CategoriesDB::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardsDao(): CardDao
}