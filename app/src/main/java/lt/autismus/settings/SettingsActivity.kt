package lt.autismus.settings

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.R
import lt.autismus.dagger.CustomViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: CustomViewModelFactory

    private val settingsViewModel by lazy {
        ViewModelProvider(this, factory).get(SettingsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.settings_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun sendImagesToDB(images: List<Uri>){
//        val source = ImageDecoder.createSource(this.contentResolver, folderUri)
//        val bitmap = ImageDecoder.decodeBitmap(source)
        setupCardsFromImages(images)
        encodeBitMapToBase64(images)
        settingsViewModel.putImagesToDB(encodeBitMapToBase64(images))
    }

    private fun setupCardsFromImages(images : List<Uri>){

    }

    private fun encodeBitMapToBase64(images: List<Uri>): List<String> {
        val imagesB64 = mutableListOf<String>()
        for (image in images){
            val imageStream: InputStream? = contentResolver.openInputStream(image)
            val selectedImageBM: Bitmap = BitmapFactory.decodeStream(imageStream)
            val byteArrayOut = ByteArrayOutputStream()
            selectedImageBM.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOut)
            val byteA = byteArrayOut.toByteArray()
            imagesB64.add(Base64.encodeToString(byteA, 0))
        }
        return imagesB64
    }
}
