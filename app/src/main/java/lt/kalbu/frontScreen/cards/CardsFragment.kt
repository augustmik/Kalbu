package lt.kalbu.frontScreen.cards

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
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import lt.kalbu.R
import lt.kalbu.dagger.CustomViewModelFactory
import lt.kalbu.databinding.DialogDeleteCardBinding
import lt.kalbu.databinding.FragmentItemListBinding
import lt.kalbu.frontScreen.MainActivity
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.util.PictureCoder
import lt.kalbu.util.TimedViewHider
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
            timedViewHider,
            requireContext()
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

        Glide.with(requireContext())
            .load(pictureCoder.decodeB64ToBitmap(card.image))
            .fitCenter()
            .into(dialogDeleteBinding.cardView.cardImage)

        dialogDeleteBinding.cardView.card = card
        deleteCardDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        deleteCardDialog.setContentView(dialogDeleteBinding.root)
        deleteCardDialog.show()
    }
}
