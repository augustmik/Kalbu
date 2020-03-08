package lt.autismus.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import lt.autismus.roomDb.dbObjects.CardDB
import lt.autismus.roomDb.dbObjects.CardDao

@Database(entities = [CardDB::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardsDao(): CardDao
}