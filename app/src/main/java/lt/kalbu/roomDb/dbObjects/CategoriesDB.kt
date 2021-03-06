package lt.kalbu.roomDb.dbObjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import lt.kalbu.singleUnits.SingleCategory

@Entity(tableName = "Categories")
data class CategoriesDB(
    @field:PrimaryKey
    val categoryName: String,

    @field:ColumnInfo(name = "imageCategory")
    val imageCardB64: String?
)

fun SingleCategory.asCategoriesDB() : CategoriesDB{
    return CategoriesDB(
        categoryName = this.name,
        imageCardB64 = this.image
    )
}

fun List<SingleCategory>.asListCategoriesDB() : List<CategoriesDB>{
    return this.map{
        CategoriesDB(
        categoryName = it.name,
        imageCardB64 = it.image
    )
    }
}

fun List<CategoriesDB>.asListSingleCategory() : List<SingleCategory>{
    return this.map {
        SingleCategory(
            name = it.categoryName,
            image = it.imageCardB64
        )
    }
}