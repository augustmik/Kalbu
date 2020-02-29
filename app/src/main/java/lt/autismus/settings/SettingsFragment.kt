package lt.autismus.settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import lt.autismus.PICKFILE_REQUEST_CODE
import lt.autismus.R
import java.io.File

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        if (preference?.key == "about") {
            selectImageFolder()
        }
        return super.onPreferenceTreeClick(preference)
    }

    private fun selectImageFolder() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        startActivityForResult(intent, PICKFILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICKFILE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Log.i("selected result", data?.dataString)
                loadImagesToList(data?.data)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun loadImagesToList(folderUri: Uri?){
        val dir = File(folderUri.toString())
        val imageFiles =  listOf(dir.listFiles())

    }
}