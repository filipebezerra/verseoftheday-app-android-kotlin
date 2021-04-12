package com.github.filipebezerra.bible.verseoftheday.data.source.remote

import androidx.lifecycle.LiveData
import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.data.source.VerseDataSource
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse
import com.github.filipebezerra.bible.verseoftheday.utils.ext.asDomainModel

class VerseRemoteDataSource(
    private val verseService: VerseService
) : VerseDataSource {

    override suspend fun getVerseOfTheDay(): Result<Verse> {
        val verseOfTheDayDto = verseService.getVerseOfTheDay("NVI")
        return Result.Success(verseOfTheDayDto.verse.asDomainModel())
    }

    override suspend fun saveVerse(verse: Verse) {
        TODO("No Op")
    }

    override fun observeVerseOfTheDayByDate(
        year: Int,
        month: Int,
        day: Int,
    ): LiveData<Result<Verse>> {
        TODO("No Op")
    }
}