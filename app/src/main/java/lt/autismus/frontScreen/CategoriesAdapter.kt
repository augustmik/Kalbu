package lt.autismus.frontScreen

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lt.autismus.R
import lt.autismus.databinding.ItemSingleCardBinding
import lt.autismus.singleUnits.SingleCard

class CategoriesAdapter (
    private var myDataSet: List<SingleCard>,
    private val context: Context
) : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myView = DataBindingUtil.inflate<ItemSingleCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_single_card,
            parent,
            false
        )
        return MyViewHolder(
            myView
        )
    }

    override fun getItemCount(): Int = myDataSet.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(myDataSet[position], context)

        holder.binding.singleCardHitbox.setOnClickListener {
//            TODO: make cards clickable here, enlarge them
        }
    }
    class MyViewHolder(val binding: ItemSingleCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: SingleCard, context: Context) {
            binding.card = card
            val decoded = Base64.decode(card.image, Base64.DEFAULT)
            val bm = BitmapFactory.decodeByteArray(decoded, 0, decoded.size)
            binding.cardImage.setImageBitmap(bm)
        }
    }

    fun renewList(items: List<SingleCard>) {
//        myDataSet.clear()
        myDataSet = items
        notifyDataSetChanged()
    }
}