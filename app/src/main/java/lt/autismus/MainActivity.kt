package lt.autismus

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import dagger.android.support.DaggerAppCompatActivity
import lt.autismus.databinding.ActivityMainBinding

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
    }
}
