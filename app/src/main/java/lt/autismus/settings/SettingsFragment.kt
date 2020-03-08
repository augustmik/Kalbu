package lt.autismus.settings

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import lt.autismus.PICKFILE_REQUEST_CODE
import lt.autismus.R


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
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//        startActivityForResult(Intent.createChooser(intent, 'Select Picture'), PICK_IMAGE)
        startActivityForResult(intent, PICKFILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICKFILE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Log.i("selected resultData", data?.data.toString())
                Log.i("selected resultClip", data?.clipData.toString())
                if (data?.data != null) {
                    sendImagesToActivity(data.data!!)
                } else {
                    sendImagesToActivity(data?.clipData!!)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun sendImagesToActivity(image: Uri){
        (requireActivity() as SettingsActivity).sendImagesToDB(listOf(image))
    }

    private fun sendImagesToActivity(images: ClipData){
        val imagesL = mutableListOf<Uri>()
        for (i in 0 until images.itemCount){
            val uri = images.getItemAt(i).uri
            imagesL.add(uri)
        }
        (requireActivity() as SettingsActivity).sendImagesToDB(imagesL)
    }
}