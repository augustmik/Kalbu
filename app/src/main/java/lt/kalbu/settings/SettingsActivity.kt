package lt.kalbu.settings

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import lt.kalbu.R
import lt.kalbu.dagger.CustomViewModelFactory
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: CustomViewModelFactory
    private var isAboutPageOpen = false

    private val settingsViewModel by lazy {
        ViewModelProvider(this, factory).get(SettingsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.settings_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()
    }

    fun openAboutPage() {
        isAboutPageOpen = true
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, AboutFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (isAboutPageOpen) {
            isAboutPageOpen = false
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_container, SettingsFragment())
                .commit()
        } else {
            onBackPressed()
        }
        return true
    }
}
