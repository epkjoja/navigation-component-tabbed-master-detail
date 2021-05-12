package com.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.myapplication.R
import com.myapplication.databinding.FragmentDetailBinding
import timber.log.Timber


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    // This property is only valid between `onCreateView` and `onDestroyView`
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.detailText.text = getString(R.string.detail_view_tab_number, args.tabNumber)
        binding.detailExtraInfoText.text = args.someExtraInfo

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
