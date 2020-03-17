package lt.autismus.frontScreen.cards

import lt.autismus.singleUnits.SingleCard

interface OnSingleCardClickedListener {
    fun onCardClickedListener(card: SingleCard)
    fun deleteCardPressed(card: SingleCard)
}