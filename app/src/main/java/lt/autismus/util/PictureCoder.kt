package lt.autismus.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.InputStream

class PictureCoder (
    private val context: Context
) {
    fun encodeBitMapToBase64(images: List<Uri>): List<String> {
        val imagesB64 = mutableListOf<String>()
        for (image in images) {
            val imageStream: InputStream? = context.contentResolver.openInputStream(image)
            val selectedImageBM: Bitmap = BitmapFactory.decodeStream(imageStream)
            val byteArrayOut = ByteArrayOutputStream()
            selectedImageBM.setHasAlpha(true)
            selectedImageBM.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOut)
            val byteA = byteArrayOut.toByteArray()
            imagesB64.add(Base64.encodeToString(byteA, Base64.DEFAULT))
        }
        return imagesB64
    }
}