package lt.autismus.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import lt.autismus.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        if (preference?.key == "about") {
            //TODO: handle preferences here
        }
        return super.onPreferenceTreeClick(preference)
    }
}