package lt.autismus.settings

import android.net.Uri
import lt.autismus.singleUnits.SingleCard

class DialogHandler (private val callback: DialogListener, val settingsViewModel: SettingsViewModel)  {
    private val cards = mutableListOf<SingleCard>()
    lateinit var images: List<Uri>
    var currentItem = 0

    fun setupFirst(imagesL: List<Uri>){
        images = imagesL
        val card = SingleCard()
        cards.add(card)
        callback.setupDialog(images[0], card)
    }

    fun loadNext(){
        currentItem++
        if (currentItem < images.size){
            val card = SingleCard()
            cards.add(card)
            callback.setupDialog(images[currentItem], card)
        } else callback.setupDialogLast(images, cards)
    }

}