package com.github.filipebezerra.bible.verseoftheday.ui.versehistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.filipebezerra.bible.verseoftheday.data.model.Verse

class VerseHistoryViewModel : ViewModel() {

    private val _verses = MutableLiveData<List<Verse>>().apply {
        value = mutableListOf(
            Verse(
                text = "então se o meu povo, que pertence somente a mim, se arrepender, abandonar os seus pecados e orar a mim, eu os ouvirei do céu, perdoarei os seus pecados e farei o país progredir de novo.",
                displayRef = "2 Crônicas 7:14",
                versionId = "NTLH",
                version = "Almeida Revista e Corrigida 2009",
                day = "23",
                month = "10",
                year = "2020"
            ),
            Verse(
                text = "então se o meu povo, que pertence somente a mim, se arrepender, abandonar os seus pecados e orar a mim, eu os ouvirei do céu, perdoarei os seus pecados e farei o país progredir de novo.",
                displayRef = "2 Crônicas 7:15",
                versionId = "NTLH",
                version = "Almeida Revista e Corrigida 2009",
                day = "22",
                month = "10",
                year = "2020"
            ),
            Verse(
                text = "então se o meu povo, que pertence somente a mim, se arrepender, abandonar os seus pecados e orar a mim, eu os ouvirei do céu, perdoarei os seus pecados e farei o país progredir de novo.",
                displayRef = "2 Crônicas 7:16",
                versionId = "NTLH",
                version = "Almeida Revista e Corrigida 2009",
                day = "21",
                month = "10",
                year = "2020"
            ),
            Verse(
                text = "então se o meu povo, que pertence somente a mim, se arrepender, abandonar os seus pecados e orar a mim, eu os ouvirei do céu, perdoarei os seus pecados e farei o país progredir de novo.",
                displayRef = "2 Crônicas 7:17",
                versionId = "NTLH",
                version = "Almeida Revista e Corrigida 2009",
                day = "20",
                month = "10",
                year = "2020"
            ),
            Verse(
                text = "então se o meu povo, que pertence somente a mim, se arrepender, abandonar os seus pecados e orar a mim, eu os ouvirei do céu, perdoarei os seus pecados e farei o país progredir de novo.",
                displayRef = "2 Crônicas 7:18",
                versionId = "NTLH",
                version = "Almeida Revista e Corrigida 2009",
                day = "19",
                month = "10",
                year = "2020"
            ),
            Verse(
                text = "então se o meu povo, que pertence somente a mim, se arrepender, abandonar os seus pecados e orar a mim, eu os ouvirei do céu, perdoarei os seus pecados e farei o país progredir de novo.",
                displayRef = "2 Crônicas 7:19",
                versionId = "NTLH",
                version = "Almeida Revista e Corrigida 2009",
                day = "18",
                month = "10",
                year = "2020"
            )
        )
    }
    val verses: LiveData<List<Verse>> = _verses
}