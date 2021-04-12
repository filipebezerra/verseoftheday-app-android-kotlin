package com.github.filipebezerra.bible.verseoftheday.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.viewModels
import com.github.filipebezerra.bible.verseoftheday.ServiceLocator
import com.github.filipebezerra.bible.verseoftheday.databinding.HomeScreenBinding
import com.github.filipebezerra.bible.verseoftheday.ui.base.FragmentBase
import com.github.filipebezerra.bible.verseoftheday.ui.home.HomeViewModel.Companion.createViewModelFactory

class HomeScreen : FragmentBase() {

    override val _viewModel: HomeViewModel by viewModels {
        createViewModelFactory(ServiceLocator.provideVerseRepository(requireContext().applicationContext))
    }

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