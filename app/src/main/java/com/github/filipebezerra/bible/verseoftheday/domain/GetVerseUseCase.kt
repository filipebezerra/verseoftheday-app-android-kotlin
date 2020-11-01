package com.github.filipebezerra.bible.verseoftheday.domain

import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.data.VerseRepository
import com.github.filipebezerra.bible.verseoftheday.data.model.Verse

class GetVerseUseCase(private val verseRepository: VerseRepository) {

    suspend operator fun invoke(
        format: String,
        version: String
    ): Result<Verse> {
        return when (val result = verseRepository.getVerse(format, version)) {
            is Result.Success -> Result.Success(result.data.verse)
            is Result.Error -> result
        }
    }
}