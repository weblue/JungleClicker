package com.twodays.jungleclicker;

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

        findPreference("pref_key_total_coconuts").setSummary(MainActivity.tree.getCoconuts() + " total coconuts generated.");

        findPreference("pref_key_coconuts_spent").setSummary(MainActivity.tree.getCoconutsSpent() + " total coconuts spent.");

        findPreference("pref_key_times_clicked").setSummary(MainActivity.tree.getTimesClicked() + " times clicked.");

        findPreference("pref_key_click_upgrades1").setSummary("Snake purchased " + MainActivity.tree.getClickUpgrades()[0] + " times.");

        findPreference("pref_key_click_upgrades2").setSummary("Giraffe purchased " + MainActivity.tree.getClickUpgrades()[1] + " times.");

        findPreference("pref_key_click_upgrades3").setSummary("Tiger purchased " + MainActivity.tree.getClickUpgrades()[2] + " times.");

        findPreference("pref_key_click_upgrades4").setSummary("Parrot purchased " + MainActivity.tree.getClickUpgrades()[3] + " times.");

        findPreference("pref_key_generate_spent0").setSummary("Gloves purchased " + MainActivity.tree.getGenUpgrades()[0] + " times.");

        findPreference("pref_key_generate_spent1").setSummary("Shovel purchased " + MainActivity.tree.getGenUpgrades()[1] + " times.");

        findPreference("pref_key_generate_spent2").setSummary("Chainsaw purchased " + MainActivity.tree.getGenUpgrades()[2] + " times.");

        findPreference("pref_key_generate_spent3").setSummary("Bulldozer purchased " + MainActivity.tree.getGenUpgrades()[3] + " times.");

        findPreference("pref_key_generate_spent4").setSummary("Excavator purchased " + MainActivity.tree.getGenUpgrades()[4] + " times.");
    }
}
