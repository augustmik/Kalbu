package lt.autismus.roomDb.dbObjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class CategoriesDB(
    @field:PrimaryKey
    val categoryName: String,

    @field:ColumnInfo(name = "imageCategory")
    val imageCardB64: String?
)