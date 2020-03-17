package lt.autismus.frontScreen.categories

import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lt.autismus.R
import lt.autismus.databinding.ItemSingleCategoryBinding
import lt.autismus.singleUnits.SingleCategory
import lt.autismus.util.PictureCoder

class CategoriesAdapter (
    private var myDataSet: List<SingleCategory>,
    private val pictureCoder: PictureCoder,
    private val onCardClickListener: OnCardClickListener,
    private val parentalMode: Boolean
) : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myView = DataBindingUtil.inflate<ItemSingleCategoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_single_category,
            parent,
            false
        )
        return MyViewHolder(
            myView, pictureCoder
        )
    }

    override fun getItemCount(): Int = myDataSet.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(myDataSet[position])

        holder.binding.singleCardHitbox.setOnClickListener {
            onCardClickListener.clickedCategory(myDataSet[position].name)
        }

        if (parentalMode) {
            holder.binding.cardClose.setOnClickListener {
                onCardClickListener.deleteCardPressed(myDataSet[position])
            }

            holder.binding.singleCardHitbox.setOnLongClickListener {
                //TODO: make haptic feedback work
                it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                holder.binding.cardClose.visibility = View.VISIBLE
                true
            }
        }
    }
    class MyViewHolder(val binding: ItemSingleCategoryBinding, private val pictureCoder: PictureCoder) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: SingleCategory) {
            binding.card = card
            binding.cardImage.setImageBitmap(pictureCoder.decodeB64ToBitmap(card.image))
        }
    }

    fun renewList(items: List<SingleCategory>) {
        myDataSet = items
        notifyDataSetChanged()
    }
}