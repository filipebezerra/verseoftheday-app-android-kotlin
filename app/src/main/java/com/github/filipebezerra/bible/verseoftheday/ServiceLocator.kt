package com.github.filipebezerra.bible.verseoftheday

import android.content.Context
import com.github.filipebezerra.bible.verseoftheday.BuildConfig.BASE_API_URL
import com.github.filipebezerra.bible.verseoftheday.data.repository.DefaultVerseRepository
import com.github.filipebezerra.bible.verseoftheday.data.repository.VerseRepository
import com.github.filipebezerra.bible.verseoftheday.data.source.VerseDataSource
import com.github.filipebezerra.bible.verseoftheday.data.source.local.VerseLocalDataSource
import com.github.filipebezerra.bible.verseoftheday.data.source.local.VotdDatabase
import com.github.filipebezerra.bible.verseoftheday.data.source.remote.VerseRemoteDataSource
import com.github.filipebezerra.bible.verseoftheday.data.source.remote.VerseService
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
    private var verseService: VerseService? = null

    @Volatile
    private var database: VotdDatabase? = null

    @Volatile
    private var verseRepository: VerseRepository? = null

    fun provideVerseRepository(context: Context): VerseRepository =
        verseRepository ?: synchronized(lock) {
            verseRepository ?: DefaultVerseRepository(
                getVerseLocalDataSource(context),
                getVerseRemoteDataSource(context)
            ).also { verseRepository = it }
        }

    private fun getVerseLocalDataSource(context: Context): VerseDataSource =
        getDatabase(context).run { VerseLocalDataSource(verseDao()) }

    private fun getDatabase(context: Context) =
        database ?: synchronized(lock) {
            database ?: VotdDatabase.getDatabase(context).also { database = it }
        }

    private fun getVerseRemoteDataSource(context: Context): VerseDataSource =
        getDatabase(context).run { VerseRemoteDataSource(getVerseService()) }

    private fun getVerseService(): VerseService =
        verseService ?: synchronized(lock) {
            verseService ?: retrofitBuilder.build().create(VerseService::class.java).also {
                verseService = it
            }
        }
}