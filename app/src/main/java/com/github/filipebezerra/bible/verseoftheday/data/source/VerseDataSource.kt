package com.github.filipebezerra.bible.verseoftheday.data.source

import androidx.lifecycle.LiveData
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse
import com.github.filipebezerra.bible.verseoftheday.data.Result

interface VerseDataSource {

    fun observeVerseOfTheDayByDate(
        year: Int,
        month: Int,
        day: Int,
    ): LiveData<Result<Verse>>

    suspend fun getVerseOfTheDay(): Result<Verse>

    suspend fun saveVerse(verse: Verse)
}