package com.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myapplication.R
import com.myapplication.databinding.FragmentMasterBinding
import timber.log.Timber


class MasterFragment : Fragment() {

    private var _binding: FragmentMasterBinding? = null
    // This property is only valid between `onCreateView` and `onDestroyView`
    private val binding get() = _binding!!

    // Must NOT be private! Args passing will fail in that case
    val args: MasterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView, args: $args")

        // Inflate the layout for this fragment
        _binding = FragmentMasterBinding.inflate(inflater, container, false)

        binding.masterText.text = "Master view, tab number: ${args.tabNumber}"
        binding.detailNavigateButton.setOnClickListener { openDetail() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDetail() {

        val isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false

        if (isTablet) {
            // On tablets, replace the detail fragment with a new-arg'd fragment
            val detail = parentFragment?.parentFragmentManager?.findFragmentById(R.id.detail_nav_fragment)
            // Override the arguments here with whatever we need to produce this detail fragment
            detail?.findNavController()
                ?.navigate(R.id.detail_fragment, getForwardArgs("I'm on a tablet"))
        } else {
            // On phones, find the main navController and navigate to detail fragment
            activity?.findNavController(R.id.content_main)?.navigate(R.id.detail_fragment, getForwardArgs("I'm on a phone"))
        }
    }

    private fun getForwardArgs(info: String): Bundle {
        return args.toBundle().apply {
            putString("some_extra_info", info)
        }
    }
}
