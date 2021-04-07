package com.github.filipebezerra.bible.verseoftheday.domain.models

data class Verse(
    val audioLink: String,
    val content: String,
    val copyright: String,
    val copyrightLink: String,
    val day: String,
    val englishReference: String,
    val merchandising: String,
    val month: String,
    val permalink: String,
    val reference: String,
    val text: String,
    val version: String,
    val versionId: String,
    val year: String,
)