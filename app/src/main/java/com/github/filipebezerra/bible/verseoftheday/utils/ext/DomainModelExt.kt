package com.github.filipebezerra.bible.verseoftheday.utils.ext

import com.github.filipebezerra.bible.verseoftheday.data.source.local.VerseEntity
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse

fun Verse.asEntity() = VerseEntity(
    id = id,
    content = content,
    day = day,
    month = month,
    permalink = permalink,
    reference = reference,
    version = version,
    versionId = versionId,
    year = year,
)