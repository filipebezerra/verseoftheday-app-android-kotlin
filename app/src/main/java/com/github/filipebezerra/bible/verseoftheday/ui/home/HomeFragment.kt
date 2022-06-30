package com.github.filipebezerra.bible.verseoftheday.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.filipebezerra.bible.verseoftheday.databinding.HomeFragmentBinding
import com.github.filipebezerra.bible.verseoftheday.extension.repeatOnLifecycleStarted
import com.github.filipebezerra.bible.verseoftheday.presentation.home.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = HomeFragmentBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repeatOnLifecycleStarted {
            homeViewModel.title.collect { title -> binding.titleText.text = title }
        }

        repeatOnLifecycleStarted {
            homeViewModel.verseOfTheDay.collect { verse ->
                with(binding) {
                    with(verse) {
                        verseText.text = content
                        referenceText.text = reference
                        bibleVersionText.text = version
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
