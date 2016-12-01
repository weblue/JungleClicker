package com.twodays.jungleclicker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by nader on 11/30/16.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences sp = getPreferenceScreen().getSharedPreferences();

        Preference pref1 = findPreference("pref_key_total_coconuts");
        pref1.setSummary(sp.getInt("pref_key_total_coconuts", 0) + " total coconuts generated.");

        Preference pref2 = findPreference("pref_key_coconuts_spent");
        pref2.setSummary(sp.getInt("pref_key_coconuts_spent", 0) + " total coconuts spent.");

        Preference pref3 = findPreference("pref_key_times_clicked");
        pref3.setSummary(sp.getInt("pref_key_times_clicked", 0) + " times clicked.");
    }
}
