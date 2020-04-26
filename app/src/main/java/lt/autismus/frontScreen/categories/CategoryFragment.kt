package lt.autismus.frontScreen.categories

import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import lt.autismus.R
import lt.autismus.dagger.CustomViewModelFactory
import lt.autismus.databinding.DialogDeleteCardBinding
import lt.autismus.databinding.DialogDeleteCategoryBinding
import lt.autismus.databinding.FragmentItemListBinding
import lt.autismus.frontScreen.MainActivity
import lt.autismus.repository.CardsRepo
import lt.autismus.singleUnits.SingleCategory
import lt.autismus.util.PictureCoder
import lt.autismus.util.TimedViewHider
import javax.inject.Inject

class CategoryFragment : DaggerFragment(), OnCardClickListener {

    @Inject
    lateinit var cardsRepo: CardsRepo

    @Inject
    lateinit var pictureCoder: PictureCoder

    @Inject
    lateinit var factory: CustomViewModelFactory

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    @Inject
    lateinit var timedViewHider: TimedViewHider

    private val deleteCardDialog: Dialog by lazy { Dialog(requireContext()) }

    lateinit var binding: FragmentItemListBinding
    private lateinit var dialogDeleteBinding: DialogDeleteCategoryBinding
    lateinit var mAdapter: CategoriesAdapter

    private val numberOfColumns = 1

    private val categoriesViewModel by lazy {
        ViewModelProvider(this, factory).get(CategoriesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_item_list,
            null,
            false
        )
        categoriesViewModel.updateCategories()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = CategoriesAdapter(
            listOf(),
            pictureCoder,
            this,
            sharedPrefs.getBoolean(getString(R.string.parental_mode_name), false),
            timedViewHider
        )

        categoriesViewModel.categoriesLive.observe(viewLifecycleOwner, Observer {
            mAdapter.renewList(it)
        })

        binding.itemRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext())//GridLayoutManager(requireContext(), numberOfColumns)
            adapter = mAdapter
        }
    }

    override fun clickedCategory(categoryName: String) {
        (requireActivity() as MainActivity).clickedCategory(categoryName)
    }

    fun notifyParentalModeChanged(parentalMode :Boolean) {
            mAdapter.notifyParentalModeChanged(parentalMode)
    }
    override fun deleteCardPressed(card: SingleCategory) {
        dialogDeleteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.dialog_delete_category,
            null,
            false
        )

        dialogDeleteBinding.acceptButton.setOnClickListener {
            categoriesViewModel.deleteCategory(card.name)
            deleteCardDialog.dismiss()
        }
        dialogDeleteBinding.cancelButton.setOnClickListener {
            deleteCardDialog.dismiss()
        }
//        dialogDeleteBinding.cardView.cardImage.setImageBitmap(pictureCoder.decodeB64ToBitmap(card.image))
        dialogDeleteBinding.cardView.cardImage.setImageURI(pictureCoder.decodeB64ToBitmap(card.image))
        dialogDeleteBinding.cardView.card = card
        deleteCardDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        deleteCardDialog.setContentView(dialogDeleteBinding.root)
        deleteCardDialog.show()
    }
}
