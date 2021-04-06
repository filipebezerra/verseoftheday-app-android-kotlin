package com.github.filipebezerra.bible.verseoftheday.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.github.filipebezerra.bible.verseoftheday.utils.ext.observeNavigationCommand

/**
 * Base Fragment to observe on the common LiveData objects
 */
abstract class FragmentBase : Fragment() {
    /**
     * Every fragment has to have an instance of a view model that extends from the BaseViewModel
     */
    abstract val _viewModel: ViewModelBase

    val navController: NavController by lazy { findNavController() }

    override fun onStart() {
        super.onStart()
        observeNavigationCommand(_viewModel.navigationCommand)
    }
}