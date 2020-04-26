package lt.autismus.frontScreen.cards

import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import lt.autismus.R
import lt.autismus.dagger.CustomViewModelFactory
import lt.autismus.databinding.DialogDeleteCardBinding
import lt.autismus.databinding.FragmentItemListBinding
import lt.autismus.frontScreen.MainActivity
import lt.autismus.singleUnits.SingleCard
import lt.autismus.util.PictureCoder
import lt.autismus.util.TimedViewHider
import javax.inject.Inject

class CardsFragment constructor(
    private val clickedCategory: String
) : DaggerFragment(), OnSingleCardClickedListener {

    @Inject
    lateinit var factory: CustomViewModelFactory

    @Inject
    lateinit var pictureCoder: PictureCoder

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    @Inject
    lateinit var timedViewHider: TimedViewHider

    private val deleteCardDialog: Dialog by lazy { Dialog(requireContext()) }

    lateinit var binding: FragmentItemListBinding
    lateinit var dialogDeleteBinding: DialogDeleteCardBinding
    lateinit var mAdapter: CardsAdapter
    private val numberOfColumns = 1

    private val cardsViewModel by lazy {
        ViewModelProvider(this, factory).get(CardsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        cardsViewModel.resetCardsLive()
        super.onCreate(savedInstanceState)
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
        cardsViewModel.updateCards(clickedCategory)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = CardsAdapter(
            listOf(),
            pictureCoder,
            this,
            sharedPrefs.getBoolean(getString(R.string.parental_mode_name), false),
            timedViewHider
        )

        cardsViewModel.cardsLive.observe(viewLifecycleOwner, Observer {
            mAdapter.renewList(it)
        })

        binding.itemRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext())//GridLayoutManager(requireContext(), numberOfColumns)
            adapter = mAdapter
        }
    }

    override fun onCardClickedListener(card: SingleCard) {
        (requireActivity() as MainActivity).displaySingleCard(card)
    }

    fun notifyParentalModeChanged(parentalMode :Boolean) {
            mAdapter.notifyParentalModeChanged(parentalMode)
    }

    override fun deleteCardPressed(card: SingleCard) {
        dialogDeleteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.dialog_delete_card,
            null,
            false
        )

        dialogDeleteBinding.acceptButton.setOnClickListener {
            cardsViewModel.deleteCard(card.id, clickedCategory)
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
