package lt.autismus.frontScreen.cards

import android.content.Context
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lt.autismus.R
import lt.autismus.databinding.ItemSingleCardBinding
import lt.autismus.singleUnits.SingleCard
import lt.autismus.util.PictureCoder
import lt.autismus.util.TimedViewHider

class CardsAdapter(
    private var myDataSet: List<SingleCard>,
    private val pictureCoder: PictureCoder,
    private val onSingleCardClickListener: OnSingleCardClickedListener,
    private var parentalMode: Boolean,
    private val timedViewHider: TimedViewHider,
    private val context: Context
) : RecyclerView.Adapter<CardsAdapter.MyViewHolder>() {

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

        holder.binding.cardDelete.setOnClickListener {
            onSingleCardClickListener.deleteCardPressed(myDataSet[position])
        }

        holder.binding.singleCardHitbox.setOnLongClickListener {
            //TODO: make haptic feedback work
            if (parentalMode) {

                it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                holder.binding.cardDelete.visibility = View.VISIBLE
                timedViewHider.hideViewTimer(holder.binding.cardDelete)
            }
            true
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

    fun notifyParentalModeChanged(parentalMode: Boolean) {
        this.parentalMode = parentalMode
    }
}