package com.github.filipebezerra.bible.verseoftheday.data

import com.github.filipebezerra.bible.verseoftheday.data.model.VerseResponse

class VerseRepository(private val remoteDataSource: VerseRemoteDataSource) {

    private val cache = mutableMapOf<Long, VerseResponse>()

    suspend fun getVerse(
        format: String,
        version: String
    ) = getData{ remoteDataSource.getVerse(format, version) }

    private suspend fun getData(
        request: suspend () -> Result<VerseResponse>
    ): Result<VerseResponse> {
        val result = request()
        if (result is Result.Success) {
            cache(result.data)
        }
        return result
    }

    private fun cache(data: VerseResponse) {
        val id = "${data.verse.year}${data.verse.month}${data.verse.day}".toLong()
        cache[id] = data
    }

    companion object {
        @Volatile
        private var INSTANCE: VerseRepository? = null

        fun getInstance(remoteDataSource: VerseRemoteDataSource): VerseRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: VerseRepository(remoteDataSource).also { INSTANCE = it }
            }
        }
    }
}