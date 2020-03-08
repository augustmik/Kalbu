package lt.autismus.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import lt.autismus.R
import lt.autismus.databinding.ActivityStoryBinding
import lt.autismus.singleUnits.SingleCard

class StoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.activity_story,
            null,
            false)

        setContentView(binding.root)

//        binding.card = SingleCard()
    }
}
