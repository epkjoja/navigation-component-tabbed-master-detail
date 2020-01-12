package com.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.myapplication.R
import kotlinx.android.synthetic.main.fragment_detail.view.*
import timber.log.Timber


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        rootView.detail_text.text = "Detail view, tab number: ${args.tabNumber}"
        rootView.detail_extra_info_text.text = args.someExtraInfo
        return rootView
    }
}
