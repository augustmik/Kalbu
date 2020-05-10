package lt.kalbu.settings

import android.net.Uri
import lt.kalbu.frontScreen.CardType
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.singleUnits.SingleCategory

class DialogHandler(
    private val callback: DialogListener,
    private val cardType: CardType,
    private val defName: String
) {
    private val cards = mutableListOf<SingleCard>()
    private val categories = mutableListOf<SingleCategory>()
    lateinit var images: List<Uri>
    var currentItem = 0

    fun setupFirst(imagesL: List<Uri>, categoryName: String?) {
        images = imagesL
        if (cardType == CardType.Card) {
            val card = SingleCard(category = categoryName)
            cards.add(card)
            callback.setupDialog(images[0], card)
        } else {
            val card = SingleCategory(defName)
            categories.add(card)
            callback.setupDialogCat(images[0], card)
        }
    }

    fun loadNext() {
        currentItem++
        if (currentItem < images.size) {
            if (cardType == CardType.Card) {
                val card = SingleCard()
                cards.add(card)
                callback.setupDialog(images[currentItem], card)
            } else {
                val card = SingleCategory(defName)
                categories.add(card)
                callback.setupDialogCat(images[currentItem], card)
            }
        } else {
            if (cardType == CardType.Card) {
                callback.setupDialogLast(images, cards)
            } else {
                callback.setupDialogCatLast(images, categories)
            }
        }
    }
}