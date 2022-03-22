package com.example.fivesecondcity

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import java.util.*


// TODO: Hook these bad boys up

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val themeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                when (newValue) {
                    "system" -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                    "light" -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    "dark" -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
                true
            }
        // TODO: Fix Locale
        val langListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                when (newValue) {
                    true -> {
                        activity?.let { LocaleHandler.setLocale(it, "cy") }
                    }
                    false -> {
                        activity?.let { LocaleHandler.setLocale(it, "en") }
                    }
                }
                true
            }

        findPreference<ListPreference>("theme")?.onPreferenceChangeListener = themeListener
        findPreference<SwitchPreference>("language")?.onPreferenceChangeListener = langListener
    }
}