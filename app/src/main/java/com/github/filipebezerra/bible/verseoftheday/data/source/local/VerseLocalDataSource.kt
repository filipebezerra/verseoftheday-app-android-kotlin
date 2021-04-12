package com.github.filipebezerra.bible.verseoftheday.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.data.Result.Success
import com.github.filipebezerra.bible.verseoftheday.data.source.VerseDataSource
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse
import com.github.filipebezerra.bible.verseoftheday.utils.ext.asDomainModel
import com.github.filipebezerra.bible.verseoftheday.utils.ext.asEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VerseLocalDataSource(
    private val verseDao: VerseDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : VerseDataSource {

    override fun observeVerseOfTheDayByDate(
        year: Int,
        month: Int,
        day: Int,
    ): LiveData<Result<Verse>> = verseDao.findVerseOfTheDayByDate(year, month, day).map {
        Success(it?.asDomainModel() ?: Verse.empty)
    }

    override suspend fun saveVerse(verse: Verse) = withContext(ioDispatcher) {
        verseDao.insert(verse.asEntity())
    }

    override suspend fun getVerseOfTheDay(): Result<Verse> {
        TODO("No Op")
    }
}