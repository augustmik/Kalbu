package lt.autismus.frontScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lt.autismus.R
import lt.autismus.databinding.ItemSingleCardBinding
import lt.autismus.singleUnits.SingleCard

class CategoriesAdapter (
    private var myDataset: MutableList<SingleCard>
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

    override fun getItemCount(): Int = 2 //change this

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(myDataset[position])

//        holder.binding.mainCv.setOnClickListener {
//            val realId: Int = myDataset[position].idTeam
//            repo.loadedTeam = myDataset[position]
//            clickListener.loadNextActivity(realId)
//
//        }
    }
    class MyViewHolder(private val binding: ItemSingleCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: SingleCard) {
            binding.card = card
        }
    }

    fun renewList(items: List<SingleCard>) {
        myDataset.clear()
        myDataset.addAll(items)
        notifyDataSetChanged()
    }
}