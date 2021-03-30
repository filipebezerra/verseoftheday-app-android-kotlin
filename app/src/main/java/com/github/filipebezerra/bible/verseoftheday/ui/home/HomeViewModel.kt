package com.github.filipebezerra.bible.verseoftheday.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _title = MutableLiveData<String>().apply {
        value = "Versículo \ndo dia"
    }
    val title: LiveData<String> = _title

    private val _verse = MutableLiveData<String>().apply {
        value = "Deem graças em todas as circunstâncias, pois esta é a vontade de Deus para vocês em Cristo Jesus."
    }
    val verse: LiveData<String> = _verse

    private val _reference = MutableLiveData<String>().apply {
        value = "1 Tessalonicenses 5:18"
    }
    val reference: LiveData<String> = _reference

    private val _bibleVersion = MutableLiveData<String>().apply {
        value = "Almeida Revista e Corrigida 2009"
    }
    val bibleVersion: LiveData<String> = _bibleVersion
}