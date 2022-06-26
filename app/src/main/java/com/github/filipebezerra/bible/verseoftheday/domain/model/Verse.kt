package com.github.filipebezerra.bible.verseoftheday.domain.model

data class Verse(
    val id: String = "",
    val content: String,
    val day: Int,
    val month: Int,
    val permalink: String,
    val reference: String,
    val version: String,
    val versionId: String,
    val year: Int
) {
    companion object {
        val empty: Verse = Verse(
            content = "",
            day = -1,
            month = -1,
            permalink = "",
            reference = "",
            version = "",
            versionId = "",
            year = -1
        )
    }
}
