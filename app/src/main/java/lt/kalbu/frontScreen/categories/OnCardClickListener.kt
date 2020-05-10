package lt.kalbu.frontScreen.categories

import lt.kalbu.singleUnits.SingleCategory

interface OnCardClickListener {
    fun clickedCategory(categoryName: String)
    fun deleteCardPressed(card: SingleCategory)
}