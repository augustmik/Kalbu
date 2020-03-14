package lt.autismus.frontScreen

import android.app.Dialog
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.PICK_GALLERY_REQUEST_CODE
import lt.autismus.R
import lt.autismus.TAKE_PICTURE_REQUEST_CODE
import lt.autismus.dagger.CustomViewModelFactory
import lt.autismus.databinding.ActivityMainBinding
import lt.autismus.databinding.DialogAddNameToCardBinding
import lt.autismus.databinding.DialogAddNameToCategoryBinding
import lt.autismus.databinding.DialogSelectSourceBinding
import lt.autismus.frontScreen.cards.CardsFragment
import lt.autismus.frontScreen.categories.CategoryFragment
import lt.autismus.settings.DialogHandler
import lt.autismus.settings.DialogListener
import lt.autismus.settings.SettingsActivity
import lt.autismus.singleUnits.SingleCard
import lt.autismus.singleUnits.SingleCategory
import lt.autismus.story.StoryActivity
import lt.autismus.util.PictureCoder
import java.io.File
import java.io.IOException
import java.text.DateFormat
import java.util.*
import javax.inject.Inject


class MainActivity @Inject constructor() : DaggerAppCompatActivity(), DialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogSelectSourceBinding

    private lateinit var dialogHandler: DialogHandler

    private val selectSourceDialog: Dialog by lazy { Dialog(this) }

    @Inject
    lateinit var factory: CustomViewModelFactory

    @Inject
    lateinit var pictureCoder: PictureCoder

    lateinit var selectedCategoryName: String
    private val mainActViewModel by lazy {
        ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_main,
            null,
            false
        )
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(
                binding.mainFragmentContainer.id,
                CategoryFragment()
            )
            .commit()
        selectedCategoryName = getString(R.string.default_category_name)

        binding.createCardButton.setOnClickListener {
            //launches create a Stories window
            val intent = Intent(this, StoryActivity::class.java)
            startActivity(intent)
        }
        binding.settingsButton.setOnClickListener {
            //launches settings window
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        setupDialog()
    }

    private fun setupDialog() {
        dialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_select_source,
            null,
            false
        )
        dialogBinding.takePictureHitbox.setOnClickListener { takeAPicture() }
        dialogBinding.loadFromGalleryHitbox.setOnClickListener { selectImagesGallery() }
        dialogBinding.backButton.setOnClickListener { selectSourceDialog.dismiss() }
        selectSourceDialog.setContentView(dialogBinding.root)

        binding.fabId.setOnClickListener {
            selectSourceDialog.show()
        }
    }

    private lateinit var takenPicUri: Uri
    private fun takeAPicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    Toast.makeText(
                        this,
                        "Klaida saugant nuotrauka.",
                        Toast.LENGTH_LONG
                    ).show()
                    null
                }
                // Continue if the File was created
                photoFile?.also {
                    takenPicUri = FileProvider.getUriForFile(
                        this,
                        "lt.autismus.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, takenPicUri)
                    startActivityForResult(takePictureIntent, TAKE_PICTURE_REQUEST_CODE)
                }
            }
        }
    }

    private lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    fun createImageFile(): File {
        // Create image file name
        val timeStamp: String = DateFormat.getTimeInstance().format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "PNG_${timeStamp}_",
            ".png",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun selectImagesGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, PICK_GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PICTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            selectSourceDialog.dismiss()
            //TODO: handle pictures

            //            val encodedImage = pictureCoder.encodeBitMapToBase64(takenPicUri)
            //            userPictures.add(encodedImage)
        }

        if (requestCode == PICK_GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            selectSourceDialog.dismiss()
            if (data?.data != null) {
                convertToUri(data.data!!)
            } else {
                convertToUri(data?.clipData!!)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun convertToUri(image: Uri) {
        setupCardsFromImages(listOf(image))
    }

    private fun convertToUri(images: ClipData) {
        val imagesL = mutableListOf<Uri>()
        for (i in 0 until images.itemCount) {
            val uri = images.getItemAt(i).uri
            imagesL.add(uri)
        }
        setupCardsFromImages(imagesL)
    }

    private fun setupCardsFromImages(images: List<Uri>) {
        val defName = getString(R.string.default_category_name)
        val fm = supportFragmentManager
        val fragment = fm.findFragmentById(binding.mainFragmentContainer.id)
        if (fragment is CategoryFragment) {
            dialogHandler = DialogHandler(this, CardType.Category, defName)
        }
        if (fragment is CardsFragment) {
            dialogHandler = DialogHandler(this, CardType.Card, defName)
        }
        dialogHandler.setupFirst(images)
    }

    override fun onBackPressed() {
        val defaultValue = getString(R.string.default_category_name)
        if (selectedCategoryName != defaultValue) {
            supportFragmentManager.beginTransaction()
                .replace(
                    binding.mainFragmentContainer.id,
                    CategoryFragment()
                ).commit()
            selectedCategoryName = defaultValue
        } else {
            super.onBackPressed()
        }
    }

    override fun setupDialog(image: Uri, cardItem: SingleCard) {
        val createCardDialog = Dialog(this)
        val createDialogBinding: DialogAddNameToCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_add_name_to_card,
            null,
            false
        )
        createDialogBinding.card = cardItem
        createDialogBinding.cardIv.setImageURI(image)
        createCardDialog.setContentView(createDialogBinding.root)
        createCardDialog.show()
        createDialogBinding.acceptButton.setOnClickListener {
            createCardDialog.dismiss()
            dialogHandler.loadNext()
        }
        createDialogBinding.cancelButton.setOnClickListener {
            createCardDialog.dismiss()
            dialogHandler.loadNext()
        }
    }

    override fun setupDialogLast(images: List<Uri>, cards: List<SingleCard>) {
        mainActViewModel.putCardsToDB(
            pictureCoder.encodeBitMapToBase64(images),
            cards,
            selectedCategoryName
        )
    }

    override fun setupDialogCat(image: Uri, categoryItem: SingleCategory) {
        val createCardDialog = Dialog(this)
        val createDialogCatBinding: DialogAddNameToCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_add_name_to_category,
            null,
            false
        )
        createDialogCatBinding.card = categoryItem
        createDialogCatBinding.cardIv.setImageURI(image)
        createCardDialog.setContentView(createDialogCatBinding.root)
        createCardDialog.show()
        createDialogCatBinding.acceptButton.setOnClickListener {
            createCardDialog.dismiss()
            dialogHandler.loadNext()
        }
        createDialogCatBinding.cancelButton.setOnClickListener {
            createCardDialog.dismiss()
            dialogHandler.loadNext()
        }
    }

    override fun setupDialogCatLast(images: List<Uri>, categories: List<SingleCategory>) {
        mainActViewModel.putCategoriesToDB(pictureCoder.encodeBitMapToBase64(images), categories)
    }

    fun clickedCategory(categoryName: String) {
        selectedCategoryName = categoryName
        supportFragmentManager.beginTransaction()
            .replace(
                binding.mainFragmentContainer.id,
                CardsFragment(categoryName)
            ).commit()
    }
}
