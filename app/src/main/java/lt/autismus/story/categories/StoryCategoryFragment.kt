package lt.autismus.story.categories

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
import lt.autismus.databinding.FragmentStoryCategoryBinding
import lt.autismus.frontScreen.categories.CategoriesViewModel
import lt.autismus.story.SelectionActivity
import lt.autismus.util.PictureCoder
import javax.inject.Inject

class StoryCategoryFragment : DaggerFragment(), OnStoryCategoryClickListener {

    @Inject
    lateinit var factory: CustomViewModelFactory

    @Inject
    lateinit var pictureCoder: PictureCoder

    lateinit var binding: FragmentStoryCategoryBinding
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
            R.layout.fragment_story_category,
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
        (requireActivity() as SelectionActivity).clickedCategory(categoryName)
    }
}
