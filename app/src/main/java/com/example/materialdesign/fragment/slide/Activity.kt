package com.example.materialdesign.fragment.slide

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityBinding

class Activity : AppCompatActivity() {
    lateinit var binding : ActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        setTabItem()
    }

    @SuppressLint("InflateParams")
    private fun setTabItem() {
        binding.tabLayout.getTabAt(0)?.customView = layoutInflater.inflate(R.layout.tab_item_earth, null)
        binding.tabLayout.getTabAt(1)?.customView = layoutInflater.inflate(R.layout.tab_item_mars,  null)
        binding.tabLayout.getTabAt(2)?.customView = layoutInflater.inflate(R.layout.tab_item_system,null)
    }
}