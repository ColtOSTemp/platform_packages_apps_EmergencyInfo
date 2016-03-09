/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.emergency.edit;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.android.emergency.PreferenceKeys;
import com.android.emergency.R;
import com.android.emergency.ReloadablePreferenceInterface;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.MetricsProto.MetricsEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment that displays personal and medical information.
 */
public class EditEmergencyInfoFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.edit_emergency_info);

        for (String preferenceKey : PreferenceKeys.KEYS_EMERGENCY_INFO) {
            Preference preference = findPreference(preferenceKey);
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object value) {
                    MetricsLogger.action(preference.getContext(),
                            MetricsEvent.ACTION_EDIT_EMERGENCY_INFO_FIELD, preference.getKey());
                    return true;
                }
            });
        }
    }

    public static Fragment newInstance() {
        return new EditEmergencyInfoFragment();
    }
}
