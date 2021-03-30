package com.github.filipebezerra.bible.verseoftheday.ui.versions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.filipebezerra.bible.verseoftheday.R
import com.github.filipebezerra.bible.verseoftheday.databinding.VersionsScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class VersionsScreen : BottomSheetDialogFragment() {
    private val viewModel: VersionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = VersionsScreenBinding.inflate(inflater, container, false)
        .apply {
            versionsList.adapter = VersionAdapter()
            viewModel = this@VersionsScreen.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}