package com.github.filipebezerra.bible.verseoftheday.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.filipebezerra.bible.verseoftheday.databinding.HomeScreenBinding

class HomeScreen : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = HomeScreenBinding.inflate(inflater, container, false)
        .apply {
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}