package lt.autismus.frontScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import lt.autismus.R

import lt.autismus.databinding.FragmentItemListBinding
import lt.autismus.singleUnits.SingleCard

class CategoryFragment : Fragment() {

    lateinit var binding : FragmentItemListBinding
    private val numberOfColumns = 1

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), numberOfColumns)
            adapter =
                CategoriesAdapter(
                    mutableListOf(SingleCard(), SingleCard())
                )
        }
    }
}
