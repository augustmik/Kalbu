package lt.autismus.frontScreen.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lt.autismus.R
import lt.autismus.databinding.ItemSingleCategoryBinding
import lt.autismus.singleUnits.SingleCategory
import lt.autismus.util.PictureCoder

class CategoriesAdapter (
    private var myDataSet: List<SingleCategory>,
    val pictureCoder: PictureCoder,
    val onCardClickListener: OnCardClickListener
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
//            TODO: make cards clickable here, enlarge them
            onCardClickListener.clickedCategory(myDataSet[position].name)

        }
    }
    class MyViewHolder(val binding: ItemSingleCategoryBinding, val pictureCoder: PictureCoder) :
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