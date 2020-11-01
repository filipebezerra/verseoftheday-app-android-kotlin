package com.github.filipebezerra.bible.verseoftheday.data.api

import com.github.filipebezerra.bible.verseoftheday.data.model.VerseResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BibleGatewayService {
    @GET("votd/get")
    suspend fun getVerse(
        @Query("format") format: String,
        @Query("version") version: String
    ): Response<VerseResponse>

    companion object {
        private const val BASE_URL = "https://www.biblegateway.com/"

        val instance: BibleGatewayService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BibleGatewayService::class.java)
        }
    }
}