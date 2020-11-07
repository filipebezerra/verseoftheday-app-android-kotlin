package com.github.filipebezerra.bible.verseoftheday.ui.todaysverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.filipebezerra.bible.verseoftheday.databinding.TodaysVerseFragmentBinding
import com.github.filipebezerra.bible.verseoftheday.ui.ServiceLocator

class TodaysVerseFragment : Fragment() {

    private val viewModel: TodaysVerseViewModel by viewModels {
        TodaysVerseViewModel.getFactory(ServiceLocator.getVerseUseCase)
    }

    private lateinit var viewBinding: TodaysVerseFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = TodaysVerseFragmentBinding.inflate(
        inflater
    ).apply {
        viewBinding = this
        this.viewModel = this@TodaysVerseFragment.viewModel
        this.lifecycleOwner = viewLifecycleOwner
    }.root
}