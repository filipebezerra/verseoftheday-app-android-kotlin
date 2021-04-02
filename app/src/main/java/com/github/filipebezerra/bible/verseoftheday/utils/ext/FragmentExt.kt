package com.github.filipebezerra.bible.verseoftheday.utils.ext

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.filipebezerra.bible.verseoftheday.utils.command.NavigationCommand
import com.github.filipebezerra.bible.verseoftheday.utils.livedata.SingleLiveEvent

fun Fragment.observeNavigationCommand(navigationCommandEvent: SingleLiveEvent<NavigationCommand>) {
    navigationCommandEvent.observe(viewLifecycleOwner) { command ->
        when (command) {
            is NavigationCommand.To -> findNavController().navigate(command.directions)
            is NavigationCommand.Back -> findNavController().popBackStack()
            is NavigationCommand.BackTo -> findNavController().popBackStack(
                command.destinationId,
                false
            )
            is NavigationCommand.ForResult -> with(command) {
                startActivityForResult(
                    intent,
                    requestCode
                )
            }
        }
    }
}