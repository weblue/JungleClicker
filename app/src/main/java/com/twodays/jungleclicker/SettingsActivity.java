package com.twodays.jungleclicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;

/**
 * Created by nader on 11/30/16.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);

        Preference pref1 = findPreference("pref_key_total_coconuts");
        pref1.setSummary(MainActivity.tree.getCoconuts() + " total coconuts generated.");

        Preference pref2 = findPreference("pref_key_coconuts_spent");
        pref2.setSummary(MainActivity.tree.getCoconutsSpent() + " total coconuts spent.");

        Preference pref3 = findPreference("pref_key_times_clicked");
        pref3.setSummary(MainActivity.tree.getTimesClicked() + " times clicked.");
    }
}
