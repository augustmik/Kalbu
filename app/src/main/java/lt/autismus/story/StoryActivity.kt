package lt.autismus.story

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.R
import lt.autismus.databinding.ActivityStoryBinding
import lt.autismus.util.PictureCoder
import javax.inject.Inject

class StoryActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var cardSelector: CardSelection

    @Inject
    lateinit var pictureCoder: PictureCoder

    lateinit var binding: ActivityStoryBinding
    lateinit var cardHelper: StoryCardHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.activity_story,
            null,
            false
        )

        setContentView(binding.root)
        setupCardSelection()
        cardHelper = StoryCardHelper(binding, pictureCoder, cardSelector)
    }

    private fun setupCardSelection() {
        binding.nowCard.setOnClickListener {
            //nowCardSelection
            val intent = Intent(this, SelectionActivity::class.java)
            intent.putExtra(getString(R.string.card_selection_extra_intent), 0)
            startActivity(intent)
        }

        binding.afterCard.setOnClickListener {
            //thenCardSelection
            val intent = Intent(this, SelectionActivity::class.java)
            intent.putExtra(getString(R.string.card_selection_extra_intent), 1)
            startActivity(intent)
        }

        cardSelector.nowCard.observe(this, Observer {
            if (it != null) {
                cardHelper.setupNowCard(it)
            }
        })
        cardSelector.thenCard.observe(this, Observer {
            if (it != null) {
                cardHelper.setupThenCard(it)
            }
        })
    }

}
