package com.github.filipebezerra.bible.verseoftheday

import androidx.annotation.VisibleForTesting
import com.github.filipebezerra.bible.verseoftheday.BuildConfig.BASE_API_URL
import com.github.filipebezerra.bible.verseoftheday.data.remote.VerseService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

object ServiceLocator {

    private val lock = Any()

    private val moshiBuilder: Moshi.Builder by lazy { Moshi.Builder() }

    private val okHttpClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level =
                HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(30, SECONDS)
            .callTimeout(60, SECONDS)
            .retryOnConnectionFailure(true)
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
            .client(okHttpClientBuilder.build())
    }

    @Volatile
    var verseService: VerseService? = null
        @VisibleForTesting set

    fun provideVerseService(): VerseService = synchronized(lock) {
        verseService ?: createVerseService()
    }

    private fun createVerseService(): VerseService =
        verseService ?: retrofitBuilder.build().create(VerseService::class.java).also {
            verseService = it
        }
}