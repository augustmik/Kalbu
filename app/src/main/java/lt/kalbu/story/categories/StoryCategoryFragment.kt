package lt.kalbu.story.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import lt.kalbu.R
import lt.kalbu.dagger.CustomViewModelFactory
import lt.kalbu.databinding.FragmentItemListBinding
import lt.kalbu.frontScreen.categories.CategoriesViewModel
import lt.kalbu.story.SelectionActivity
import lt.kalbu.util.PictureCoder
import javax.inject.Inject

class StoryCategoryFragment : DaggerFragment(), OnStoryCategoryClickListener {

    @Inject
    lateinit var factory: CustomViewModelFactory

    @Inject
    lateinit var pictureCoder: PictureCoder

    lateinit var binding: FragmentItemListBinding
    private lateinit var mAdapter: StoryCategoriesAdapter

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
        mAdapter = StoryCategoriesAdapter(
            listOf(),
            pictureCoder,
            this,
            requireContext()
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
        (requireActivity() as SelectionActivity).clickedCategory(categoryName)
    }
}
