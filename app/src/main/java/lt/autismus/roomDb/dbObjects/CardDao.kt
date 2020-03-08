package lt.autismus.roomDb.dbObjects

import androidx.room.*

@Dao
interface CardDao {

    @Query("SELECT * FROM Cards")
    fun getAll(): List<CardDB>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cards: List<CardDB>)
//    @Query("SELECT * FROM Players WHERE idTeam = (:teamId)")
//    fun loadAllByTeamId(teamId: Int): List<PlayerDB>

//    @Delete
//    fun delete(user: PlayerDB)
}