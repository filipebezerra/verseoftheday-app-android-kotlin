package com.github.filipebezerra.bible.verseoftheday.ui.todaysverse

import androidx.lifecycle.*
import com.github.filipebezerra.bible.verseoftheday.data.Result
import com.github.filipebezerra.bible.verseoftheday.data.model.Verse
import com.github.filipebezerra.bible.verseoftheday.domain.GetVerseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.unbescape.html.HtmlEscape
import java.util.*

class TodaysVerseViewModel(private val getVerseUseCase: GetVerseUseCase) : ViewModel() {

    private val _verseOfTheDay = MutableLiveData<Verse>()
    val verseOfTheDay: LiveData<Verse> = _verseOfTheDay

    private val _formattedVerseText = MutableLiveData<String>()
    val formattedVerseText: LiveData<String> = _formattedVerseText

    private val _loadingVerse = MutableLiveData<Boolean>()
    val loadingVerse: LiveData<Boolean>
        get() = _loadingVerse

    init {
        loadVerse()
    }

    private fun loadVerse() = launchDataLoad(
        getVerse = {
            getVerseUseCase.invoke("json", "ARC")
        },
        unescapeHtml = {
            withContext(Dispatchers.IO) {
                HtmlEscape.unescapeHtml(it)
            }
        }
    )

    private fun launchDataLoad(
        getVerse: suspend () -> Result<Verse>,
        unescapeHtml: suspend (verse: String) -> String
    ) {
        viewModelScope.launch {
            _loadingVerse.value = true
            val result = getVerse()
            var unescapedHtml: String? = null
            if (result is Result.Success) {
                unescapedHtml = unescapeHtml(result.data.text)
            }

            when (result) {
                is Result.Success -> {
                    _verseOfTheDay.value = result.data
                    _formattedVerseText.value = unescapedHtml
                        ?.substring(1, unescapedHtml.length - 1)
                        ?.capitalize(Locale.getDefault()) ?: result.data.text
                }
                is Result.Error -> result.exception
            }
            _loadingVerse.value = false
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        @Volatile
        private var FACTORY: ViewModelProvider.Factory? = null

        fun getFactory(getVerseUseCase: GetVerseUseCase): ViewModelProvider.Factory {
            return FACTORY ?: synchronized(this) {
                FACTORY ?: object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        if (modelClass.isAssignableFrom(TodaysVerseViewModel::class.java)) {
                            return TodaysVerseViewModel(getVerseUseCase) as T
                        }
                        throw IllegalArgumentException("Unknown view model class $modelClass")
                    }
                }
            }
        }
    }
}