package com.github.filipebezerra.bible.verseoftheday.utils.ext

import com.github.filipebezerra.bible.verseoftheday.data.remote.VerseOfTheDayTransferObject
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse

fun VerseOfTheDayTransferObject.asDomainModel(): Verse = Verse(
    audioLink = verse.audio_link,
    content = verse.content,
    copyright = verse.copyright,
    copyrightLink = verse.copyright_link,
    day = verse.day,
    englishReference = verse.english_reference,
    merchandising = verse.merchandising,
    month = verse.month,
    permalink = verse.permalink,
    reference = verse.reference,
    text = verse.text,
    version = verse.version,
    versionId = verse.version_id,
    year = verse.year,
)