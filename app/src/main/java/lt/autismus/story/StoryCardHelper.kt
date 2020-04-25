package lt.autismus.story

import android.view.View
import lt.autismus.databinding.ActivityStoryBinding
import lt.autismus.singleUnits.SingleCard
import lt.autismus.util.PictureCoder
import lt.autismus.util.TimedViewHider

class StoryCardHelper (
    val binding: ActivityStoryBinding,
    val pictureCoder: PictureCoder,
    private val cardSelector: CardSelection,
    private val timedViewHider: TimedViewHider
) {

    fun setupNowCard(card: SingleCard){
        binding.nowCardSelection = card
//        binding.nowCardImage.setImageBitmap(pictureCoder.decodeB64ToBitmap(card.image))
        binding.nowCardImage.setImageURI(pictureCoder.decodeB64ToBitmap(card.image))

        binding.nowCard.setOnLongClickListener {
            binding.nowCardCancel.visibility = View.VISIBLE
            timedViewHider.hideViewTimer(binding.nowCardCancel)
            true
        }

        binding.nowCardCancel.setOnClickListener {
            cardSelector.resetNowCardSelector()
        }
    }

    fun setupThenCard(card: SingleCard){
        binding.thenCardSelection = card
//        binding.afterCardImage.setImageBitmap(pictureCoder.decodeB64ToBitmap(card.image))
        binding.afterCardImage.setImageURI(pictureCoder.decodeB64ToBitmap(card.image))

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