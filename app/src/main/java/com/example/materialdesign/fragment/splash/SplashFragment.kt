package com.example.materialdesign.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.materialdesign.R

class SplashFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.splash_fragment,container,false)
        val linearLayout: LinearLayout = layout.findViewById(R.id.linearLayout)

        linearLayout.addView( CanvasAnimation(requireContext()) )
        return layout
    }

        companion object {
        fun newInstance() = SplashFragment()
    }
}