package com.github.filipebezerra.bible.verseoftheday.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.filipebezerra.bible.verseoftheday.domain.model.Verse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _title = MutableStateFlow("Versículo \ndo dia")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _verseOfTheDay = MutableStateFlow(Verse.empty)
    val verseOfTheDay: StateFlow<Verse> = _verseOfTheDay.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _verseOfTheDay.value = Verse(
                content = "Não te vingarás, nem guardarás ira contra os filhos do teu povo; mas amarás o teu próximo como a ti mesmo. Eu sou o Senhor.",
                day = 26,
                month = 6,
                permalink = "https://www.biblegateway.com/passage/?search=Leviticus%2019:18&amp;version=ARC",
                reference = "Leviticus 19:18",
                version = "Almeida Revista e Corrigida 2009",
                versionId = "ARC",
                year = 2022
            )
        }
    }
}
