package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_tabs.view.*
import timber.log.Timber


class ItemTabsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")

        val rootView = inflater.inflate(R.layout.fragment_tabs, container,false)

        rootView.viewpager.adapter = TabsPagerAdapter(this,3)

        TabLayoutMediator(rootView.tab_layout, rootView.viewpager) { tab, pos ->
            tab.text = "TAB ${pos + 1}"
        }.attach()

        return rootView
    }
}
