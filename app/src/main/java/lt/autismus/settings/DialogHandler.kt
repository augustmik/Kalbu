package lt.autismus.settings

import android.app.Dialog
import android.net.Uri
import lt.autismus.singleUnits.SingleCard

class DialogHandler (private val callback: DialogListener)  {
    val cards = mutableListOf<SingleCard>()
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
        }
    }

}