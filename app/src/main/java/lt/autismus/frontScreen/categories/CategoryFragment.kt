package lt.autismus.frontScreen.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import lt.autismus.R
import lt.autismus.dagger.CustomViewModelFactory
import lt.autismus.databinding.FragmentItemListBinding
import lt.autismus.frontScreen.MainActivity
import lt.autismus.repository.CardsRepo
import lt.autismus.singleUnits.SingleCategory
import lt.autismus.util.PictureCoder
import javax.inject.Inject

class CategoryFragment : DaggerFragment(), OnCardClickListener {

    @Inject
    lateinit var cardsRepo: CardsRepo

    @Inject
    lateinit var pictureCoder: PictureCoder

    @Inject
    lateinit var factory: CustomViewModelFactory

    lateinit var binding: FragmentItemListBinding
    private val numberOfColumns = 1
//    private lateinit var takenPicUri: Uri

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
        val mAdapter = CategoriesAdapter(
            listOf(),
            pictureCoder,
            this
        )

        categoriesViewModel.categoriesLive.observe(viewLifecycleOwner, Observer {
            mAdapter.renewList(it)
        })

        binding.itemRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())//GridLayoutManager(requireContext(), numberOfColumns)
            adapter = mAdapter
        }
    }

    override fun clickedCategory(categoryName: String) {
        (requireActivity() as MainActivity).clickedCategory(categoryName)
    }
}
