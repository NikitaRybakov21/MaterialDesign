package com.example.materialdesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentOptionsBinding
import com.example.materialdesign.main.MainActivity
import com.example.materialdesign.main.SavedOptions

class OptionsFragment : Fragment() {
    private var _binding: FragmentOptionsBinding? = null
    private val binding: FragmentOptionsBinding get() = _binding!!

    private val KEY = "Theme"

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOptions()
    }

    private fun checkOptions() = with(binding){
        val options = SavedOptions.getOptions(activity as MainActivity)
        val op = options.load(KEY)

        switchDark.isChecked = op

        switchDark.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                options.save(KEY,true)
                (activity as MainActivity).setNewTheme()
            } else {
                options.save(KEY,false)
                (activity as MainActivity).setNewTheme()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = OptionsFragment()
    }
}