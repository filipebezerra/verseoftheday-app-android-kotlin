package com.github.filipebezerra.bible.verseoftheday.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.viewModels
import com.github.filipebezerra.bible.verseoftheday.databinding.HomeScreenBinding
import com.github.filipebezerra.bible.verseoftheday.ui.base.BaseFragment

class HomeScreen : BaseFragment() {
    override val _viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = HomeScreenBinding.inflate(inflater, container, false)
        .apply {
            viewModel = _viewModel
            lifecycleOwner = viewLifecycleOwner
            shareButton.setOnClickListener {
                _viewModel.onShareButtonClicked(ShareCompat.IntentBuilder.from(requireActivity()))
            }
        }
        .root
}