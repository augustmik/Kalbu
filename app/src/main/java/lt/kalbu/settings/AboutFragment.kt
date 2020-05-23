package lt.kalbu.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lt.kalbu.R
import lt.kalbu.databinding.FragmentAboutUsBinding

class AboutFragment : Fragment() {

    lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_about_us,
            null,
            false
        )
        return binding.root
    }

}
