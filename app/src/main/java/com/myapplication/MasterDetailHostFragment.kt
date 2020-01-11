package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber


class MasterDetailHostFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false

        Timber.d("onCreateView - isTablet: $isTablet")

        val rootView = if (isTablet)
            inflater.inflate(R.layout.fragment_master_detail_host, container,false)
        else
            inflater.inflate(R.layout.fragment_master_host, container,false)

        // Timber.d("Args: $arguments")

        val master = childFragmentManager.findFragmentById(R.id.master_nav_fragment) as NavHostFragment?
        master?.navController?.setGraph(R.navigation.master, arguments)

        if (isTablet) {
            val detail = childFragmentManager.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
            detail?.navController?.setGraph(R.navigation.detail, arguments)
        }

        return rootView
    }


    companion object {
        val TAB_NUMBER = "tab_number"
        val SOME_EXTRA_INFO  = "some_extra_info"

        fun newInstance(tabNumber: Int): MasterDetailHostFragment {
            val fragment = MasterDetailHostFragment()

            val bundle = Bundle().apply {
                putInt(TAB_NUMBER, tabNumber)
                putString(SOME_EXTRA_INFO, "I am probably on a tablet")
            }

            fragment.arguments = bundle

            return fragment
        }
    }
}
