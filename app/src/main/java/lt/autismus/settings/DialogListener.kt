package lt.autismus.settings

import android.net.Uri
import lt.autismus.singleUnits.SingleCard
import lt.autismus.singleUnits.SingleCategory

interface DialogListener {
    fun setupDialog (image: Uri, cardItem : SingleCard)
    fun setupDialogLast(images: List<Uri>, cards  : List<SingleCard>)
    fun setupDialogCat (image: Uri, categoryItem : SingleCategory)
    fun setupDialogCatLast(images: List<Uri>, categories  : List<SingleCategory>)
    }