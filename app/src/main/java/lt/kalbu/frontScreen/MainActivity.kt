package lt.kalbu.frontScreen

import android.app.Dialog
import android.content.ClipData
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import lt.kalbu.*
import lt.kalbu.dagger.CustomViewModelFactory
import lt.kalbu.databinding.*
import lt.kalbu.frontScreen.cards.CardsFragment
import lt.kalbu.frontScreen.categories.CategoryFragment
import lt.kalbu.settings.DialogHandler
import lt.kalbu.settings.DialogListener
import lt.kalbu.settings.SettingsActivity
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.singleUnits.SingleCategory
import lt.kalbu.story.StoryActivity
import lt.kalbu.util.PictureCoder
import java.io.File
import java.io.IOException
import java.text.DateFormat
import java.util.*
import javax.inject.Inject


class MainActivity @Inject constructor() : DaggerAppCompatActivity(), DialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogSelectSourceBinding

    private lateinit var dialogHandler: DialogHandler
    private lateinit var nameOfShared: String

    private val selectSourceDialog: Dialog by lazy { Dialog(this) }

    @Inject
    lateinit var factory: CustomViewModelFactory

    @Inject
    lateinit var pictureCoder: PictureCoder

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    private lateinit var selectedCategoryName: String
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
        resetTitleToCategory()

        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(
                binding.mainFragmentContainer.id,
                CategoryFragment()
            )
            .commit()

        nameOfShared = getString(R.string.parental_mode_name)
        resetParentalMode()
        selectedCategoryName = getString(R.string.default_category_name)
        checkForFirstLaunch()
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

    private fun checkForFirstLaunch(){
        if (sharedPrefs.getBoolean(IS_FIRST_TIME_LAUNCH_PREF, true)){
            mainActViewModel.fistTimeLaunchLoader(this)
            sharedPrefs.edit().putBoolean(IS_FIRST_TIME_LAUNCH_PREF, false).apply()
        }
    }

    private fun resetParentalMode() {
        val editor = sharedPrefs.edit()
        editor.putBoolean(nameOfShared, false).apply()
    }

    override fun onResume() {
        val liveFragment = supportFragmentManager.findFragmentById(binding.mainFragmentContainer.id)
        val parentalMode = sharedPrefs.getBoolean(nameOfShared, false)
        if (liveFragment is CardsFragment) {
            liveFragment.notifyParentalModeChanged(parentalMode)
        } else (liveFragment as CategoryFragment).notifyParentalModeChanged(parentalMode)

        if (parentalMode) {
            binding.createCardButton.visibility = View.VISIBLE
            binding.fabId.visibility = View.VISIBLE
        } else {
            binding.createCardButton.visibility = View.GONE
            binding.fabId.visibility = View.GONE
        }
        super.onResume()
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
        selectSourceDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
                        "lt.kalbu.fileprovider",
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
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, PICK_GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PICTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            selectSourceDialog.dismiss()
            setupCardsFromImages(listOf(takenPicUri))
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
        dialogHandler.setupFirst(images, selectedCategoryName)
    }

    private fun resetTitleToCategory() {
        binding.titlePage.text = getString(R.string.category_page_title)
    }

    override fun onBackPressed() {
        val defaultValue = getString(R.string.default_category_name)
        if (selectedCategoryName != defaultValue) {
            supportFragmentManager.beginTransaction()
                .replace(
                    binding.mainFragmentContainer.id,
                    CategoryFragment()
                ).commit()
            resetTitleToCategory()
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

        Glide.with(this)
            .load(image)
            .fitCenter()
            .into(createDialogBinding.cardIv)

        createCardDialog.setContentView(createDialogBinding.root)
        createCardDialog.show()
        createDialogBinding.acceptButton.setOnClickListener {
            createCardDialog.dismiss()
            dialogHandler.loadNext()
        }
        createDialogBinding.cancelButton.setOnClickListener {
            createCardDialog.dismiss()
            //TODO: add func to just cancel that single one card, not all of them
//            dialogHandler.loadNext()
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

        Glide.with(this)
            .load(image)
            .fitCenter()
            .into(createDialogCatBinding.cardIv)

        createCardDialog.setContentView(createDialogCatBinding.root)
        createCardDialog.show()
        createDialogCatBinding.acceptButton.setOnClickListener {
            if (categoryItem.name == getString(R.string.default_category_name)) {
                Toast.makeText(this, "Klaida! Pavadinimas tuscias!", Toast.LENGTH_LONG).show()
            } else {
                if (checkIfNameDoesntExist(categoryItem.name)) {
                    createCardDialog.dismiss()
                    dialogHandler.loadNext()

                } else {
                    Toast.makeText(this, getString(R.string.category_already_exists_error), Toast.LENGTH_LONG).show()
                }
            }
        }
        createDialogCatBinding.cancelButton.setOnClickListener {
            createCardDialog.dismiss()
//            dialogHandler.loadNext()
        }
    }

    private fun checkIfNameDoesntExist(selectedCategory: String): Boolean {
        var answer = true
        val fm = supportFragmentManager
        val fragment = fm.findFragmentById(binding.mainFragmentContainer.id) as CategoryFragment
        val namesTaken = fragment.mAdapter.getNames()
        if (namesTaken.contains(selectedCategory)) answer = false
        return answer
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
        binding.titlePage.text = categoryName
    }

    fun displaySingleCard(cardItem: SingleCard) {
        val createEnlargerDialog = Dialog(this)
        val createEnlargerDialogBinding: FragmentSelectedCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.fragment_selected_card,
            null,
            false
        )
        createEnlargerDialogBinding.card = cardItem

        Glide.with(this)
            .load(pictureCoder.decodeB64ToBitmap(cardItem.image))
            .fitCenter()
            .into(createEnlargerDialogBinding.cardImage)

        createEnlargerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        createEnlargerDialog.window?.setDimAmount(BIGGER_DIM_AMOUNT)
        createEnlargerDialog.setContentView(createEnlargerDialogBinding.root)
        createEnlargerDialog.show()
    }
}
