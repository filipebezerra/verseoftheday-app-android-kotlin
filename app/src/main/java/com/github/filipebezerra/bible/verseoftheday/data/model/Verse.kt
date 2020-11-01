package com.github.filipebezerra.bible.verseoftheday.data.model

import android.text.format.DateUtils
import com.google.gson.annotations.SerializedName
import org.unbescape.html.HtmlEscape
import java.util.*

data class VerseResponse(
    @field:SerializedName("votd") val verse: Verse
)

data class Verse(
    @field:SerializedName("text") val text: String,
//    @field:SerializedName("content") val content: String,
    @field:SerializedName("display_ref") val displayRef: String,
//    @field:SerializedName("reference") val reference: String,
//    @field:SerializedName("permalink") val permalink: String,
//    @field:SerializedName("copyright") val copyright: String,
//    @field:SerializedName("copyrightlink") val copyrightLink: String,
//    @field:SerializedName("audiolink") val audioLink: String,
    @field:SerializedName("day") val day: String,
    @field:SerializedName("month") val month: String,
    @field:SerializedName("year") val year: String,
    @field:SerializedName("version") val version: String,
    @field:SerializedName("version_id") val versionId: String,
//    @field:SerializedName("merchandising") val merchandising: String,
) {
    private val timeStamp: Long
        get() = Calendar.getInstance().apply {
            set(
                year.toInt(),
                month.toInt() - 1,
                day.toInt(),
                0,
                0,
                59
            )
        }.timeInMillis

    val relativeTimeSpan: CharSequence = DateUtils.getRelativeTimeSpanString(
        timeStamp,
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS
    )
}