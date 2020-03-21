package lt.autismus.story

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.R
import lt.autismus.databinding.ActivitySelectionBinding
import lt.autismus.frontScreen.cards.CardsFragment
import lt.autismus.singleUnits.SingleCard
import lt.autismus.story.cards.StoryCardsFragment
import lt.autismus.story.categories.StoryCategoryFragment
import javax.inject.Inject

class SelectionActivity @Inject constructor() : DaggerAppCompatActivity() {

    @Inject
    lateinit var cardSelector : CardSelection

    lateinit var binding: ActivitySelectionBinding
    private lateinit var selectedCategoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.activity_selection,
            null,
            false)

        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(
                binding.mainFragmentContainer.id,
                StoryCategoryFragment()
            )
            .commit()
    }

    fun clickedCategory(categoryName: String) {
        selectedCategoryName = categoryName
        supportFragmentManager.beginTransaction()
            .replace(
                binding.mainFragmentContainer.id,
                StoryCardsFragment(categoryName)
            ).commit()
    }

    fun cardSelected(card: SingleCard){
        when (intent.getIntExtra(getString(R.string.card_selection_extra_intent), -1)){
            0 -> cardSelector.updateNowCard(card)
            1 -> cardSelector.updateThenCard(card)
            else -> Toast.makeText(this, "Klaida pasirenkant kortelę", Toast.LENGTH_LONG).show()
        }
        finish()
    }
}
