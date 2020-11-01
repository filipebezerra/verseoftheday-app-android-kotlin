package com.github.filipebezerra.bible.verseoftheday.ui.todaysverse

import androidx.lifecycle.*
import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.data.VerseRemoteDataSource
import com.github.filipebezerra.bible.verseoftheday.data.VerseRepository
import com.github.filipebezerra.bible.verseoftheday.data.api.BibleGatewayService
import com.github.filipebezerra.bible.verseoftheday.data.model.Verse
import com.github.filipebezerra.bible.verseoftheday.domain.GetVerseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.unbescape.html.HtmlEscape
import java.sql.Struct

class TodaysVerseViewModel : ViewModel() {

    private val getVerseUseCase: GetVerseUseCase by lazy {
        GetVerseUseCase(
            VerseRepository.getInstance(
                VerseRemoteDataSource.getInstance(
                    BibleGatewayService.instance
                )
            )
        )
    }

    private val _verseOfTheDay = MutableLiveData<Verse>()
    val verseOfTheDay: LiveData<Verse> = _verseOfTheDay

    private val _formattedVerseText = MutableLiveData<String>()
    val formattedVerseText: LiveData<String> = _formattedVerseText

    init {
        loadVerse()
    }

    private fun loadVerse() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getVerseUseCase.invoke("json", "ARC")
            var unescapedHtml: String? = null
            if (result is Result.Success) {
                unescapedHtml = HtmlEscape.unescapeHtml(result.data.text)
            }

            viewModelScope.launch {
                when (result) {
                    is Result.Success -> {
                        _verseOfTheDay.value = result.data
                        _formattedVerseText.value = unescapedHtml ?: result.data.text
                    }
                    is Result.Error -> result.exception
                }
            }
        }
    }
}