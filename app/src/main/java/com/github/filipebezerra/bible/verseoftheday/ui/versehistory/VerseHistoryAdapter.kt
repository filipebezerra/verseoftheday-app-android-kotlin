package com.github.filipebezerra.bible.verseoftheday.ui.versehistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.filipebezerra.bible.verseoftheday.data.model.Verse
import com.github.filipebezerra.bible.verseoftheday.databinding.ListItemVerseBinding

class VerseHistoryAdapter : ListAdapter<Verse, VerseHistoryAdapter.FeedViewHolder>(FeedDiffItemCallback()) {

    class FeedViewHolder private constructor(
        private val viewBinding: ListItemVerseBinding
    ): RecyclerView.ViewHolder(
        viewBinding.root
    ) {
        fun bind(verse: Verse) {
            viewBinding.verse = verse
            viewBinding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup): FeedViewHolder {
                val viewBinding = ListItemVerseBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FeedViewHolder(viewBinding)
            }
        }
    }

    class FeedDiffItemCallback : DiffUtil.ItemCallback<Verse>() {
        override fun areItemsTheSame(oldItem: Verse, newItem: Verse) =
            oldItem.displayRef == newItem.displayRef

        override fun areContentsTheSame(oldItem: Verse, newItem: Verse) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FeedViewHolder.create(parent)

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) =
        holder.bind(getItem(position))
}