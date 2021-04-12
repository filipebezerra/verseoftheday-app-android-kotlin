package com.github.filipebezerra.bible.verseoftheday.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.filipebezerra.bible.verseoftheday.DATABASE_NAME

@Database(
    entities = [
        VerseEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class VotdDatabase : RoomDatabase() {

    abstract fun verseDao(): VerseDao

    companion object {
        @Volatile
        private var databaseInstance: VotdDatabase? = null

        fun getDatabase(context: Context): VotdDatabase =
            databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabase(context).also {
                    databaseInstance = it
                }
            }

        private fun buildDatabase(context: Context): VotdDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                VotdDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}