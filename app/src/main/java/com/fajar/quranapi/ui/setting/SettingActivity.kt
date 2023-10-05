package com.fajar.quranapi.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.fajar.quranapi.R
import com.fajar.quranapi.ui.utils.CHANNEL_ID
import com.fajar.quranapi.ui.utils.NOTIFICATION_CHANNEL_ID

import java.util.concurrent.TimeUnit

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preference, rootKey)

            val prefPrayerTimeReminder =
                findPreference<SwitchPreference>(getString(R.string.notify_channel_name))
            val prayerTimeReminder = PrayerTimeReminder()

            prefPrayerTimeReminder?.setOnPreferenceChangeListener { _, newValue ->
                if (newValue == true) {
                    // Enable prayer time reminder
                    context?.let { prayerTimeReminder.setPrayerTimeReminder(it) }
                } else {
                    // Disable prayer time reminder
                    context?.let { prayerTimeReminder.cancelPrayerTimeReminder(it) }
                }
                true
            }
        }
    }
}