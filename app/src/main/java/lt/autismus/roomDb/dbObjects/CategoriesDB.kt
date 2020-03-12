package lt.autismus.roomDb.dbObjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import lt.autismus.singleUnits.SingleCategory

@Entity(tableName = "Categories")
data class CategoriesDB(
    @field:PrimaryKey
    val categoryName: String?,

    @field:ColumnInfo(name = "imageCategory")
    val imageCardB64: String?
)

fun SingleCategory.asCategoriesDB() : CategoriesDB{
    return CategoriesDB(
        categoryName = this.name,
        imageCardB64 = this.image
    )
}

fun List<CategoriesDB>.asListSingleCategory() : List<SingleCategory>{
    return this.map {
        SingleCategory(
            name = it.categoryName,
            image = it.imageCardB64
        )
    }
}