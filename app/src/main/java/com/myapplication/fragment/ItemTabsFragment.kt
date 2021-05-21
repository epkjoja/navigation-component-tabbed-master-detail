package com.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.myapplication.databinding.FragmentTabsBinding
import timber.log.Timber


class ItemTabsFragment : Fragment() {

    private var _binding: FragmentTabsBinding? = null
    // This property is only valid between `onCreateView` and `onDestroyView`
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")

        // Inflate the layout for this fragment
        _binding = FragmentTabsBinding.inflate(inflater, container, false)

        binding.viewpager.adapter = TabsPagerAdapter(this,3)

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, pos ->
            tab.text = "TAB ${pos + 1}"
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class TabsPagerAdapter(fragment: Fragment, private val numTabs: Int): FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = numTabs

        override fun createFragment(position: Int): Fragment {
            Timber.d("createFragment: $position")

            return MasterDetailHostFragment.newInstance(position + 1)
        }
    }

}
