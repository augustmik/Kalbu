package lt.autismus.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import lt.autismus.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}