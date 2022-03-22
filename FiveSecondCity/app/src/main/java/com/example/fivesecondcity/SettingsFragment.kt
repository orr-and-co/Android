package com.example.fivesecondcity

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import java.util.*


// TODO: Hook these bad boys up

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val pref = findPreference<ListPreference>("theme")
        val changeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                if (preference.key == "theme") {
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
                }
                else if(preference.key == "language")
                {
                    val res: Resources = context?.getResources()!!
                    val dm: DisplayMetrics = res.getDisplayMetrics()
                    val conf: Configuration = res.getConfiguration()
                    if(newValue == "true")
                    {
                        conf.locale = Locale("cy")
                        res.updateConfiguration(conf, dm)
                    }
                    else
                    {
                        conf.locale = Locale("en")
                        res.updateConfiguration(conf, dm)
                    }
                }
                true
            }
        if (pref != null) {
            pref.setOnPreferenceChangeListener(changeListener)
        }
    }
}