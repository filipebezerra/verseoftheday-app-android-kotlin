package com.github.filipebezerra.bible.verseoftheday.ui.versions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.filipebezerra.bible.verseoftheday.models.BibleVersion

@BindingAdapter("versions")
fun RecyclerView.bindVersions(versions: List<BibleVersion>) = apply {
    (adapter as VersionAdapter).submitList(versions)
}