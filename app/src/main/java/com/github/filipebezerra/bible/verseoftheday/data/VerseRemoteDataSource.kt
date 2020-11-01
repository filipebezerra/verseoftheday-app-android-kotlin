package com.github.filipebezerra.bible.verseoftheday.data

import com.github.filipebezerra.bible.verseoftheday.data.api.BibleGatewayService
import com.github.filipebezerra.bible.verseoftheday.data.model.VerseResponse
import retrofit2.Response
import java.io.IOException

class VerseRemoteDataSource(private val service: BibleGatewayService) {

    suspend fun getVerse(
        format: String,
        version: String
    ): Result<VerseResponse> {
        return try {
            val response = service.getVerse(format, version)
            getResult(response, onError = {
                Result.Error(
                    IOException("Error getting verse ${response.code()} ${response.message()}")
                )
            })
        } catch (e: Exception) {
            Result.Error(IOException("Error getting verse", e))
        }
    }

    private inline fun getResult(
        response: Response<VerseResponse>,
        onError: () -> Result.Error
    ): Result<VerseResponse> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return onError.invoke()
    }

    companion object {
        @Volatile
        private var INSTANCE: VerseRemoteDataSource? = null

        fun getInstance(service: BibleGatewayService): VerseRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: VerseRemoteDataSource(service).also { INSTANCE = it }
            }
        }
    }
}