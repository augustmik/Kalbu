package lt.kalbu.settings

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.getColor
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import lt.kalbu.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        findPreference<Preference>("about")?.summary = "versija " + requireContext().packageManager.getPackageInfo(requireContext().packageName, 0).versionName
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        if (preference?.key == "download_images") {
            openCustomChromeTabsPage(Uri.parse(getString(R.string.add_more_images_link)))
        }
        if (preference?.key == "about") {
            (activity as SettingsActivity).openAboutPage()
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