package lt.autismus.frontScreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.R
import lt.autismus.databinding.ActivityMainBinding
import lt.autismus.settings.SettingsActivity
import lt.autismus.story.StoryActivity

class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_main,
            null,
            false
        )
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(binding.mainFragmentContainer.id, CategoryFragment())
            .commit()


        binding.createCardButton.setOnClickListener {
            //launches create a card window
            val intent = Intent(this, StoryActivity::class.java)
            startActivity(intent)
        }
        binding.settingsButton.setOnClickListener {
            //launches settings window
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

//        binding.
    }
}
