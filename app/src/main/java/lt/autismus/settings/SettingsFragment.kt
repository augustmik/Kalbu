package lt.autismus.settings

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.getColor
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import lt.autismus.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        if (preference?.key == "download_images") {
            openCustomChromeTabsPage(Uri.parse(getString(R.string.add_more_images_link)))
        }
        if (preference?.key == "about") {
            //TODO: handle preferences here
        }
        return super.onPreferenceTreeClick(preference)
    }
    private fun openCustomChromeTabsPage(page: Uri) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(getColor(requireContext(), R.color.colorPrimary))
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), page)
    }
}