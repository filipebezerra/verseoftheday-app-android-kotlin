package com.github.filipebezerra.bible.verseoftheday.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class VerseDao {

    @Insert
    abstract fun insert(verse: VerseEntity)

    @Query("SELECT * FROM verses WHERE year = :year AND month = :month AND day = :day LIMIT 1")
    abstract fun findVerseOfTheDayByDate(
        year: Int,
        month: Int,
        day: Int
    ): LiveData<VerseEntity?>
}