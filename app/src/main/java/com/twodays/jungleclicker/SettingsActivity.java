package com.twodays.jungleclicker;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by nader on 11/30/16.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
