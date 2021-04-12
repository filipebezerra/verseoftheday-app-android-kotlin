package com.github.filipebezerra.bible.verseoftheday.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "verses")
data class VerseEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "day") val day: Int,
    @ColumnInfo(name = "month") val month: Int,
    @ColumnInfo(name = "permalink") val permalink: String,
    @ColumnInfo(name = "reference") val reference: String,
    @ColumnInfo(name = "version") val version: String,
    @ColumnInfo(name = "versionId") val versionId: String,
    @ColumnInfo(name = "year") val year: Int,
)
