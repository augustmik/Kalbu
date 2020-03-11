package lt.autismus.settings

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.R
import lt.autismus.dagger.CustomViewModelFactory
import lt.autismus.databinding.DialogAddNameToCardBinding
import lt.autismus.singleUnits.SingleCard
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity(), DialogListener {

    @Inject
    lateinit var factory: CustomViewModelFactory

    private lateinit var dialogBinding: DialogAddNameToCardBinding
    private lateinit var dialogHandler : DialogHandler

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

    fun sendImagesToDB(images: List<Uri>) {
        setupCardsFromImages(images)
    }

    private fun setupCardsFromImages(images: List<Uri>) {
        dialogHandler = DialogHandler(this, settingsViewModel)
        dialogHandler.setupFirst(images)
    }

    override fun setupDialog(image: Uri, cardItem: SingleCard) {
        val createCardDialog = Dialog(this)
        dialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_add_name_to_card,
            null,
            false
        )
        dialogBinding.card = cardItem
        dialogBinding.cardIv.setImageURI(image)
        createCardDialog.setContentView(dialogBinding.root)
        createCardDialog.show()
        dialogBinding.acceptButton.setOnClickListener {
            createCardDialog.dismiss()
            dialogHandler.loadNext()
        }
        dialogBinding.cancelButton.setOnClickListener {
            createCardDialog.dismiss()
            dialogHandler.loadNext()
        }
    }
    override fun setupDialogLast(images: List<Uri>, cards  : List<SingleCard>){
        settingsViewModel.putItemsToDB(encodeBitMapToBase64(images), cards)
    }

    private fun encodeBitMapToBase64(images: List<Uri>): List<String> {
        val imagesB64 = mutableListOf<String>()
        for (image in images) {
            val imageStream: InputStream? = contentResolver.openInputStream(image)
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
