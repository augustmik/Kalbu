package lt.autismus.settings

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.lifecycle.ViewModel
import lt.autismus.roomDb.RoomDBService
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import javax.inject.Inject


class SettingsViewModel @Inject constructor(
    private val roomService : RoomDBService
): ViewModel() {
    fun putImagesToDB(images: List<String>){

    }
}