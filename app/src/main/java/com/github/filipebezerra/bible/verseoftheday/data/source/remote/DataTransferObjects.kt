package com.github.filipebezerra.bible.verseoftheday.data.source.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VerseTransferObject(
    @Json(name = "audio_link") val audio_link: String,
    @Json(name = "content") val content: String,
    @Json(name = "copyright") val copyright: String,
    @Json(name = "copyright_link") val copyright_link: String,
    @Json(name = "day") val day: Int,
    @Json(name = "english_reference") val english_reference: String,
    @Json(name = "merchandising") val merchandising: String,
    @Json(name = "month") val month: Int,
    @Json(name = "permalink") val permalink: String,
    @Json(name = "reference") val reference: String,
    @Json(name = "text") val text: String,
    @Json(name = "version") val version: String,
    @Json(name = "version_id") val version_id: String,
    @Json(name = "year") val year: Int,
)

@JsonClass(generateAdapter = true)
data class VerseLinksTransferObject(
    @Json(name = "audio_link") val audio_link: String,
    @Json(name = "copyright_link") val copyright_link: String,
    @Json(name = "self") val self: String,
)

@JsonClass(generateAdapter = true)
data class VerseOfTheDayTransferObject(
    @Json(name = "_links") val links: VerseLinksTransferObject,
    @Json(name = "votd") val verse: VerseTransferObject,
)