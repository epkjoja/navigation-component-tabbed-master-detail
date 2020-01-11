package com.myapplication

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import timber.log.Timber

class TabsPagerAdapter(fragment: Fragment, private val numTabs: Int)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = numTabs

    override fun createFragment(position: Int): Fragment {
        Timber.d("createFragment: $position")

        return MasterDetailHostFragment.newInstance(position + 1)
    }
}
