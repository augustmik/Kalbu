package lt.kalbu.story

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import lt.kalbu.databinding.ActivityStoryBinding
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.util.PictureCoder
import lt.kalbu.util.TimedViewHider

class StoryCardHelper (
    val binding: ActivityStoryBinding,
    val pictureCoder: PictureCoder,
    private val cardSelector: CardSelection,
    private val timedViewHider: TimedViewHider
) {

    fun setupNowCard(card: SingleCard, context: Context){
        binding.nowCardSelection = card

        Glide.with(context)
            .load(pictureCoder.decodeB64ToBitmap(card.image))
            .fitCenter()
            .into(binding.nowCardImage)

        binding.nowCard.setOnLongClickListener {
            binding.nowCardCancel.visibility = View.VISIBLE
            timedViewHider.hideViewTimer(binding.nowCardCancel)
            true
        }

        binding.nowCardCancel.setOnClickListener {
            cardSelector.resetNowCardSelector()
        }
    }

    fun setupThenCard(card: SingleCard, context: Context){
        binding.thenCardSelection = card

        Glide.with(context)
            .load(pictureCoder.decodeB64ToBitmap(card.image))
            .fitCenter()
            .into(binding.afterCardImage)

        binding.afterCard.setOnLongClickListener {
            binding.afterCardCancel.visibility = View.VISIBLE
            timedViewHider.hideViewTimer(binding.afterCardCancel)
            true
        }

        binding.afterCardCancel.setOnClickListener {
            cardSelector.resetThenCardSelector()
        }
    }
}