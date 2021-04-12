package com.github.filipebezerra.bible.verseoftheday.data.repository

import androidx.lifecycle.LiveData
import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse
import java.time.LocalDate

interface VerseRepository {

    fun observeVerseOfTheDayByDate(
        year: Int = LocalDate.now().year,
        month: Int = LocalDate.now().monthValue,
        day: Int = LocalDate.now().dayOfMonth
    ): LiveData<Result<Verse>>

    suspend fun getVerseOfTheDay(): Result<Verse>
}