package com.github.filipebezerra.bible.verseoftheday.ui.versehistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.filipebezerra.bible.verseoftheday.databinding.FragmentVerseHistoryBinding

class VerseHistoryFragment : Fragment() {

    private val verseHistoryViewModel: VerseHistoryViewModel by viewModels()

    private lateinit var feedViewBinding: FragmentVerseHistoryBinding

    private lateinit var verseHistoryAdapter: VerseHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentVerseHistoryBinding.inflate(
        inflater
    ).apply {
        this.lifecycleOwner = viewLifecycleOwner
        feedViewBinding = this
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeVerseList()
    }

    private fun observeVerseList() {
        createFeedAdapter()
        verseHistoryViewModel.verses.observe(viewLifecycleOwner) {
            verseHistoryAdapter.submitList(it)
        }
    }

    private fun createFeedAdapter() {
        verseHistoryAdapter = VerseHistoryAdapter()
        feedViewBinding.listVerses.adapter = verseHistoryAdapter
    }
}