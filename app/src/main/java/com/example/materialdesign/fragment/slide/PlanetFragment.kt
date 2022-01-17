package com.example.materialdesign.fragment.slide

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentPlanetBinding

class PlanetFragment : Fragment() {
    private var _binding : FragmentPlanetBinding? = null
    private val binding : FragmentPlanetBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlanetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = activity?.let { it -> ViewPagerAdapter(it.supportFragmentManager) }
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        setTabItem()
    }

    @SuppressLint("InflateParams")
    private fun setTabItem() {
        binding.tabLayout.getTabAt(0)?.customView = layoutInflater.inflate(R.layout.tab_item_earth, null)
        binding.tabLayout.getTabAt(1)?.customView = layoutInflater.inflate(R.layout.tab_item_mars,  null)
        binding.tabLayout.getTabAt(2)?.customView = layoutInflater.inflate(R.layout.tab_item_system,null)
    }

    companion object{
        fun newInstance() = PlanetFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}