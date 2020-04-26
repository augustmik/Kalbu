package lt.autismus.story.cards

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lt.autismus.R
import lt.autismus.databinding.ItemSingleCardBinding
import lt.autismus.singleUnits.SingleCard
import lt.autismus.util.PictureCoder

class StoryCardsAdapter(
    private var myDataSet: List<SingleCard>,
    private val pictureCoder: PictureCoder,
    private val onSingleCardClickListener: OnStoryCardClickedListener,
    private val context: Context
) : RecyclerView.Adapter<StoryCardsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myView = DataBindingUtil.inflate<ItemSingleCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_single_card,
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
            onSingleCardClickListener.onCardClickedListener(myDataSet[position])
        }
    }

    class MyViewHolder(val binding: ItemSingleCardBinding, private val pictureCoder: PictureCoder) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: SingleCard, context: Context) {
            binding.card = card

            Glide.with(context)
                .load(pictureCoder.decodeB64ToBitmap(card.image))
                .fitCenter()
                .into(binding.cardImage)
        }
    }

    fun renewList(items: List<SingleCard>) {
        myDataSet = items
        notifyDataSetChanged()
    }
}