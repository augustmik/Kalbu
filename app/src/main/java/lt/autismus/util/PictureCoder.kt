package lt.autismus.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.InputStream

class PictureCoder (
    private val context: Context
) {
    private val encodingType = Base64.DEFAULT

    fun encodeBitMapToBase64(images: List<Uri>): List<String> {
        val imagesB64 = mutableListOf<String>()
        for (image in images) {
            val imageStream: InputStream? = context.contentResolver.openInputStream(image)
            val selectedImageBM: Bitmap = BitmapFactory.decodeStream(imageStream)
            val byteArrayOut = ByteArrayOutputStream()
            selectedImageBM.setHasAlpha(true)
            Log.i("Byte Count: ", selectedImageBM.byteCount.toString())
            val sizeInMb = selectedImageBM.byteCount / 1000000
            if (sizeInMb > 2){
                selectedImageBM.compress(Bitmap.CompressFormat.JPEG, 100/sizeInMb, byteArrayOut)
            }
            else{
                selectedImageBM.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOut)
            }
            Log.i("Resized: ", selectedImageBM.byteCount.toString())
            val byteA = byteArrayOut.toByteArray()
            imagesB64.add(Base64.encodeToString(byteA, encodingType))
        }
        return imagesB64
    }

    fun decodeB64ToBitmap(image: String?) : Bitmap{
        val decoded = Base64.decode(image, encodingType)
        return BitmapFactory.decodeByteArray(decoded, 0, decoded.size)
    }
}