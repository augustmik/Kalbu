package lt.autismus.roomDb.dbObjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import lt.autismus.singleUnits.SingleCard

@Entity(tableName = "Cards", foreignKeys = [ForeignKey(
    entity = CategoriesDB::class,
    parentColumns = arrayOf("categoryName"),
    childColumns = arrayOf("cardCategory"),
    onDelete = ForeignKey.CASCADE
)])
data class CardDB (
    @field:PrimaryKey(autoGenerate = true)
    val idCard : Int,

    @field:ColumnInfo(name = "textCard")
    val textCard : String?,

    @field:ColumnInfo(name = "imageCardB64")
    val imageCardB64 : String?,

    @field:ColumnInfo(name = "cardCategory")
    val category : String?
)
fun List<SingleCard>.asListCardDB() : List<CardDB>{
    return this.map {
        CardDB(
            idCard = it.id,
            textCard = it.title,
            imageCardB64 = it.image,
            category = it.category
        )
    }
}

fun List<CardDB>.asListCard() : List<SingleCard>{
    return this.map {
        SingleCard(
            id = it.idCard,
            title = it.textCard,
            image = it.imageCardB64
        )
    }
}