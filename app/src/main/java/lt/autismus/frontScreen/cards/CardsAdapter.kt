package lt.autismus.frontScreen.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lt.autismus.R
import lt.autismus.databinding.ItemSingleCardBinding
import lt.autismus.singleUnits.SingleCard
import lt.autismus.util.PictureCoder

class CardsAdapter (
    private var myDataSet: List<SingleCard>,
    private val pictureCoder: PictureCoder
) : RecyclerView.Adapter<CardsAdapter.MyViewHolder>(){

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

        holder.bind(myDataSet[position])

        holder.binding.singleCardHitbox.setOnClickListener {
//            TODO: make cards clickable here, enlarge them
        }
    }
    class MyViewHolder(val binding: ItemSingleCardBinding, val pictureCoder: PictureCoder) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: SingleCard) {
            binding.card = card
            binding.cardImage.setImageBitmap(pictureCoder.decodeB64ToBitmap(card.image))
        }
    }

    fun renewList(items: List<SingleCard>) {
        myDataSet = items
        notifyDataSetChanged()
    }
}