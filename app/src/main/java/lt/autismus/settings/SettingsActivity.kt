package lt.autismus.settings

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.R
import lt.autismus.dagger.CustomViewModelFactory
import lt.autismus.databinding.DialogAddNameToCardBinding
import lt.autismus.singleUnits.SingleCard
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: CustomViewModelFactory

    private val createCardDialog: Dialog by lazy { Dialog(this) }

    private lateinit var dialogBinding: DialogAddNameToCardBinding

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

        dialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_add_name_to_card,
            null,
            false
        )
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
        for (item in images){
            val cardItem = SingleCard()
            dialogBinding.card = cardItem
            dialogBinding.cardIv.setImageURI(item)
            createCardDialog.setContentView(dialogBinding.root)
            createCardDialog.show()
//            addPicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
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
