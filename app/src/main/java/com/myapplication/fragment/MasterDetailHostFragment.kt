package com.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.myapplication.R
import com.myapplication.databinding.FragmentHostSinglePaneBinding
import com.myapplication.databinding.FragmentHostTwoPaneBinding
import timber.log.Timber


class MasterDetailHostFragment : Fragment() {

    private var _binding: ViewBinding? = null
    // This property is only valid between `onCreateView` and `onDestroyView`
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false

        Timber.d("onCreateView - isTablet: $isTablet")

        // Inflate the layout for this fragment
        _binding = if (isTablet)
            FragmentHostTwoPaneBinding.inflate(inflater, container, false)
        else
            FragmentHostSinglePaneBinding.inflate(inflater, container, false)

        val master = childFragmentManager.findFragmentById(R.id.master_nav_fragment)
        master?.findNavController()?.navigate(R.id.master_fragment, arguments)

        if (isTablet) {
            val detail = childFragmentManager.findFragmentById(R.id.detail_nav_fragment)
            detail?.findNavController()?.navigate(R.id.detail_fragment, arguments)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val TAB_NUMBER = "tab_number"
        private const val SOME_EXTRA_INFO = "some_extra_info"

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
