package com.example.fivesecondcity

import android.app.UiModeManager.MODE_NIGHT_NO
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.preference.Preference
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

// TODO: Hook these bad boys up

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val pref = findPreference<ListPreference>("theme")
        // TODO: Set listener
    }


    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (preference.key == "theme")
        {
            if (newValue == "light")
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        return true
    }
}