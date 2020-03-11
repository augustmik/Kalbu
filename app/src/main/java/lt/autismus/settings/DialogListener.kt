package lt.autismus.settings

import android.net.Uri
import lt.autismus.singleUnits.SingleCard

interface DialogListener {
    fun setupDialog (image: Uri, cardItem : SingleCard)
    fun setupDialogLast(images: List<Uri>, cards  : List<SingleCard>)
    }