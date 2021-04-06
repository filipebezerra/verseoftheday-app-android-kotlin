package com.github.filipebezerra.bible.verseoftheday.ui.base

import androidx.lifecycle.ViewModel
import com.github.filipebezerra.bible.verseoftheday.utils.command.NavigationCommand
import com.github.filipebezerra.bible.verseoftheday.utils.livedata.SingleLiveEvent

/**
 * Base class for View Models to declare the common LiveData objects in one place
 */
abstract class ViewModelBase : ViewModel() {
    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
}