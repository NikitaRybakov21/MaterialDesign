package com.example.materialdesign.fragment.slide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val EARTH_FRAGMENT  = 0
private const val MARS_FRAGMENT   = 1
private const val SYSTEM_FRAGMENT = 2

// TODO
class ViewPagerAdapter(private val fm : FragmentManager) : FragmentStatePagerAdapter(fm)  {
    private val fragments = arrayOf(EarthFragment(),MarsFragment(),SystemFragment())

    override fun getCount() = fragments.size

    override fun getItem(position: Int): Fragment {
        return when(position){
            EARTH_FRAGMENT  -> fragments[position]
            MARS_FRAGMENT   -> fragments[position]
            SYSTEM_FRAGMENT -> fragments[position]
            else -> fragments[EARTH_FRAGMENT]
        }
    }

 /* override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            EARTH_FRAGMENT  -> "EARTH"
            MARS_FRAGMENT   -> "MARS"
            SYSTEM_FRAGMENT -> "SYSTEM"
            else -> "EARTH"
        }
    } */
}