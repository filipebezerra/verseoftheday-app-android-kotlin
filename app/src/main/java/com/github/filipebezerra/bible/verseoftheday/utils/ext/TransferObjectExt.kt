package com.github.filipebezerra.bible.verseoftheday.utils.ext

import com.github.filipebezerra.bible.verseoftheday.data.source.remote.VerseTransferObject
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse

fun VerseTransferObject.asDomainModel() = Verse(
    content = content,
    day = day,
    month = month,
    permalink = permalink,
    reference = reference,
    version = version,
    versionId = version_id,
    year = year,
)