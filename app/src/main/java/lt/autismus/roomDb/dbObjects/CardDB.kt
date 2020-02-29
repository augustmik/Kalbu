package lt.autismus.roomDb.dbObjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cards")
data class CardDB (
    @field:PrimaryKey
    val idCard : Int?,

    @field:ColumnInfo(name = "textCard")
    val textCard : String?,

    @field:ColumnInfo(name = "imageCardB64")
    val imageCardB64 : String?
)