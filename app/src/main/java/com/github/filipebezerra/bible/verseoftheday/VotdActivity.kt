package com.github.filipebezerra.bible.verseoftheday

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.filipebezerra.bible.verseoftheday.databinding.VotdActivityBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.time.LocalDate
import java.time.LocalTime
import com.github.filipebezerra.bible.verseoftheday.VotdNavigationDirections.Companion.actionGlobalVersions as toVersions

class VotdActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    private lateinit var viewBinding: VotdActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<VotdActivityBinding>(this, R.layout.votd_activity)
            .apply {
                viewBinding = this
                val appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.home_screen,
                        R.id.history_screen,
                    )
                )
                setupActionBarWithNavController(navController, appBarConfiguration)

                with(viewBinding.navView) {
                    setupWithNavController(navController)
                    getOrCreateBadge(R.id.history_screen)
                        .apply {
                            isVisible = true
                            setContentDescriptionNumberless(getString(R.string.nav_view_badge_content_description))
                        }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.votd_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.action_select_bible_version -> { navController.navigate(toVersions()).run { true } }
            R.id.action_set_remember_to_read -> {
                val now = LocalTime.now()
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(now.hour)
                    .setMinute(now.minute)
                    .setTitleText("Receber versículo às")
                    .build()
                    .show(supportFragmentManager, "timePicker")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}