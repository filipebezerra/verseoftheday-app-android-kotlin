package com.github.filipebezerra.bible.verseoftheday.ui.home

import androidx.core.app.ShareCompat
import androidx.lifecycle.*
import com.github.filipebezerra.bible.verseoftheday.data.remote.VerseService
import com.github.filipebezerra.bible.verseoftheday.domain.models.Verse
import com.github.filipebezerra.bible.verseoftheday.ui.base.ViewModelBase
import com.github.filipebezerra.bible.verseoftheday.utils.ext.asDomainModel
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    verseService: VerseService,
) : ViewModelBase() {

    val verseOfTheDay: LiveData<Verse> = liveData(Dispatchers.IO) {
        emit(verseService.getVerseOfTheDay("NVI").asDomainModel())
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
        fun createViewModelFactory(verseService: VerseService) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    HomeViewModel(verseService) as T
            }
    }
}