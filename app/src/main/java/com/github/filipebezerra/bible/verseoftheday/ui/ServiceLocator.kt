package com.github.filipebezerra.bible.verseoftheday.ui

import com.github.filipebezerra.bible.verseoftheday.data.VerseRemoteDataSource
import com.github.filipebezerra.bible.verseoftheday.data.VerseRepository
import com.github.filipebezerra.bible.verseoftheday.data.api.BibleGatewayService
import com.github.filipebezerra.bible.verseoftheday.domain.GetVerseUseCase

object ServiceLocator {
    val getVerseUseCase: GetVerseUseCase by lazy {
        GetVerseUseCase(
            VerseRepository.getInstance(
                VerseRemoteDataSource.getInstance(
                    BibleGatewayService.instance
                )
            )
        )
    }
}