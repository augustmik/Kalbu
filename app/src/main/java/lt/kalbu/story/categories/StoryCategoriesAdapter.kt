package lt.kalbu.story.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lt.kalbu.R
import lt.kalbu.databinding.ItemSingleCategoryBinding
import lt.kalbu.singleUnits.SingleCategory
import lt.kalbu.util.PictureCoder

class StoryCategoriesAdapter(
    private var myDataSet: List<SingleCategory>,
    private val pictureCoder: PictureCoder,
    private val onCardClickListener: OnStoryCategoryClickListener,
    private val context: Context
) : RecyclerView.Adapter<StoryCategoriesAdapter.MyViewHolder>() {

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

        holder.bind(myDataSet[position], context)

        holder.binding.singleCardHitbox.setOnClickListener {
            onCardClickListener.clickedCategory(myDataSet[position].name)
        }
    }

    class MyViewHolder(
        val binding: ItemSingleCategoryBinding,
        private val pictureCoder: PictureCoder
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: SingleCategory, context : Context) {
            binding.card = card

            Glide.with(context)
                .load(pictureCoder.decodeB64ToBitmap(card.image))
                .fitCenter()
                .into(binding.cardImage)
        }
    }

    fun renewList(items: List<SingleCategory>) {
        myDataSet = items
        notifyDataSetChanged()
    }
}