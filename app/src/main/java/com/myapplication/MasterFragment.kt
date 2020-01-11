package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_master.view.*
import timber.log.Timber


class MasterFragment : Fragment() {

    // Must NOT be private! Args passing will fail otherwise
    val args: MasterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView, args: $args")

        val rootView = inflater.inflate(R.layout.fragment_master, container, false)

        rootView.master_text.text = "Master view, tab number: ${args.tabNumber}"
        rootView.detail_navigate_button.setOnClickListener { openDetail() }
        return rootView
    }

    fun openDetail() {

        val isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false

        if (isTablet) {
            // On tablets, replace the detail fragment with a new-arg'd fragment
            swapDetailFragment()
        } else {
            // On phones, launch a new detail activity
            findNavController().navigate(
                MasterFragmentDirections.showDetailsFromMaster(args.tabNumber, "I am on a phone")
            )
        }
    }

    private fun swapDetailFragment() {
        val forwardedArguments = args.toBundle()
        forwardedArguments.putString("some_extra_info", "I am on a tablet")
        Timber.d("Args: $forwardedArguments")

        val detail = parentFragment?.fragmentManager?.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
        // Override the arguments here with whatever we need to produce this detail fragment
        detail?.navController?.setGraph(R.navigation.detail, forwardedArguments)
    }
}
