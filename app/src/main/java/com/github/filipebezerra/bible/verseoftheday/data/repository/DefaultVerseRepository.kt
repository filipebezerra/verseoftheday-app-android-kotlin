package com.github.filipebezerra.bible.verseoftheday.data.repository

import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.data.Result.Success
import com.github.filipebezerra.bible.verseoftheday.data.source.VerseDataSource
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultVerseRepository(
    private val localDataSource: VerseDataSource,
    private val remoteDataSource: VerseDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : VerseRepository {

    override fun observeVerseOfTheDayByDate(year: Int, month: Int, day: Int) =
        localDataSource.observeVerseOfTheDayByDate(year, month, day)

    override suspend fun getVerseOfTheDay(): Result<Verse> = withContext(ioDispatcher) {
        val result = remoteDataSource.getVerseOfTheDay()
        if (result is Success) {
            localDataSource.saveVerse(result.data)
        }
        result
    }
}