package lt.kalbu.settings

import android.net.Uri
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.singleUnits.SingleCategory

interface DialogListener {
    fun setupDialog (image: Uri, cardItem : SingleCard)
    fun setupDialogLast(images: List<Uri>, cards  : List<SingleCard>)
    fun setupDialogCat (image: Uri, categoryItem : SingleCategory)
    fun setupDialogCatLast(images: List<Uri>, categories  : List<SingleCategory>)
    }