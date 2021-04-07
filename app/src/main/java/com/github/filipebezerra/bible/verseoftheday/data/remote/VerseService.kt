package com.github.filipebezerra.bible.verseoftheday.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface VerseService {

    @GET("votd/{version}")
    suspend fun getVerseOfTheDay(@Path("version") version: String): VerseOfTheDayTransferObject
}