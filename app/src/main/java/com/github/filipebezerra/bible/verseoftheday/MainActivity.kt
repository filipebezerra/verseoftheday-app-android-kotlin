package com.github.filipebezerra.bible.verseoftheday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.filipebezerra.bible.verseoftheday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_verse_history,
                R.id.navigation_todays_verse
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        binding.navView.getOrCreateBadge(binding.navView.menu.findItem(R.id.navigation_todays_verse).itemId)
            .apply { isVisible = true }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            changeTitleWhen(destination)
        }
    }

    private fun changeTitleWhen(destination: NavDestination) {
        when (destination.id) {
            R.id.navigation_todays_verse -> supportActionBar?.title = getString(R.string.app_name)
        }
    }
}