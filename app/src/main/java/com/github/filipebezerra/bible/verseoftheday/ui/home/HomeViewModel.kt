package com.github.filipebezerra.bible.verseoftheday.ui.home

import androidx.core.app.ShareCompat
import androidx.lifecycle.*
import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.data.repository.VerseRepository
import com.github.filipebezerra.bible.verseoftheday.data.source.remote.VerseService
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse
import com.github.filipebezerra.bible.verseoftheday.ui.base.ViewModelBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    verseRepository: VerseRepository
) : ViewModelBase() {

    val verseOfTheDay: LiveData<Verse> = verseRepository.observeVerseOfTheDayByDate().map { result ->
        if (result is Result.Success) {
            result.data
        } else {
            Verse.empty
        }
    }

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                verseRepository.getVerseOfTheDay()
            }
        }
    }

    fun onShareButtonClicked(intentBuilder: ShareCompat.IntentBuilder) =
        verseOfTheDay.value?.let { verse ->
            intentBuilder
                .setType("plain/text")
                .setText(
                    StringBuilder("Encontrei um verso bíblico para você...")
                        .append(verse.content)
                        .append(" em ")
                        .append(verse.reference)
                        .appendLine(".")
                        .append("Leia diversos outros no #VersículoDoDia.")
                )
                .startChooser()
        }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun createViewModelFactory(verseRepository: VerseRepository) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    HomeViewModel(verseRepository) as T
            }
    }
}