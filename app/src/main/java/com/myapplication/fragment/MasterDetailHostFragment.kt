package com.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.myapplication.R
import timber.log.Timber


class MasterDetailHostFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false

        Timber.d("onCreateView - isTablet: $isTablet")

        val rootView = if (isTablet)
            inflater.inflate(R.layout.fragment_host_two_pane, container,false)
        else
            inflater.inflate(R.layout.fragment_host_single_pane, container,false)

        val master = childFragmentManager.findFragmentById(R.id.master_nav_fragment) as NavHostFragment?
        master?.navController?.setGraph(R.navigation.master, arguments)

        if (isTablet) {
            val detail = childFragmentManager.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
            detail?.navController?.setGraph(R.navigation.detail, arguments)
        }

        return rootView
    }


    companion object {
        private const val TAB_NUMBER = "tab_number"
        private const val SOME_EXTRA_INFO  = "some_extra_info"

        fun newInstance(tabNumber: Int): MasterDetailHostFragment {

            return MasterDetailHostFragment().apply {
                arguments = bundleOf(
                        Pair(TAB_NUMBER, tabNumber),
                        Pair(SOME_EXTRA_INFO, "I am probably on a tablet")
                )
            }
        }
    }
}
