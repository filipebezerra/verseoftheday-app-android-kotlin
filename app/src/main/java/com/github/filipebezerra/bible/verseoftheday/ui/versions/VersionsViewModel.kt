package com.github.filipebezerra.bible.verseoftheday.ui.versions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.filipebezerra.bible.verseoftheday.domain.models.BibleVersion

class VersionsViewModel : ViewModel() {
    private val _versions = MutableLiveData<List<BibleVersion>>()
    val versions: LiveData<List<BibleVersion>>
        get() = _versions

    init {
        _versions.value = listOf(
            BibleVersion("Almeida Revista e Atualizada"),
            BibleVersion("Nova Versão Internacional"),
            BibleVersion("King James Atualizada")
        )
    }
}