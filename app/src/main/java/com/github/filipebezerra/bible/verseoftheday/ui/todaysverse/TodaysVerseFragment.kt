package com.github.filipebezerra.bible.verseoftheday.ui.todaysverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.filipebezerra.bible.verseoftheday.databinding.FragmentTodaysVerseBinding

class TodaysVerseFragment : Fragment() {

    private val todaysVerseViewModel: TodaysVerseViewModel by viewModels()

    private lateinit var viewBinding: FragmentTodaysVerseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentTodaysVerseBinding.inflate(
        inflater
    ).apply {
        this.viewModel = todaysVerseViewModel
        this.lifecycleOwner = viewLifecycleOwner
        viewBinding = this
    }.root
}