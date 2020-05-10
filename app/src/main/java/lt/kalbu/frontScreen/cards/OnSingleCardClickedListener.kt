package lt.kalbu.frontScreen.cards

import lt.kalbu.singleUnits.SingleCard

interface OnSingleCardClickedListener {
    fun onCardClickedListener(card: SingleCard)
    fun deleteCardPressed(card: SingleCard)
}