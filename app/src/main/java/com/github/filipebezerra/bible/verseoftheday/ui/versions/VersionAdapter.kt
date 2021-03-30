package com.github.filipebezerra.bible.verseoftheday.ui.versions

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.filipebezerra.bible.verseoftheday.databinding.VersionListItemBinding
import com.github.filipebezerra.bible.verseoftheday.databinding.VersionListItemBinding.inflate
import com.github.filipebezerra.bible.verseoftheday.models.BibleVersion
import com.github.filipebezerra.bible.verseoftheday.ui.versions.VersionViewHolder.Companion.createFrom

class VersionAdapter : RecyclerView.Adapter<VersionViewHolder>() {
    private val versions = mutableListOf<BibleVersion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionViewHolder =
        createFrom(parent)

    override fun onBindViewHolder(holder: VersionViewHolder, position: Int) {
        holder.bindTo(versions[position])
    }

    override fun getItemCount(): Int {
        return versions.size
    }

    fun submitList(versionList: List<BibleVersion>) {
        versions.clear()
        versions.addAll(versionList)
        notifyDataSetChanged()
    }
}

class VersionViewHolder(
    private val itemViewBinding: VersionListItemBinding
) : RecyclerView.ViewHolder(itemViewBinding.root) {
    fun bindTo(item: BibleVersion) {
        with(itemViewBinding) {
            version = item
            executePendingBindings()
        }
    }

    companion object {
        fun createFrom(parent: ViewGroup) = VersionViewHolder(
            inflate(from(parent.context), parent, false)
        )
    }
}