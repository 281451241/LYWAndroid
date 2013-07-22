package com.example.togglebuttonprference;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.example.preference.R;

public class MainActivity extends PreferenceActivity {
    private ToggleButtonPrference mToggleButtonPrference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pre);
        mToggleButtonPrference = (ToggleButtonPrference) findPreference("set_gogglebutton_key");

//        Log.e("hefeng", "mToggleButtonPrference isCheck on=" + mToggleButtonPrference.defaultValues());

        mToggleButtonPrference.setToggleButtonOnClickListener(new ToggleButtonPrference.ToggleButtonOnClickListener() {
            @Override
            public void onClick(boolean isCheck) {
                // TODO Auto-generated method stub
                Log.e("hefeng", "mToggleButtonPrference isCheck=" + isCheck);
            }
        });
        mToggleButtonPrference.setOnPreferenceChangeListener(new OnPreferenceChangeListener()
		{
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue)
			{
				Log.e("hefeng", "mToggleButtonPrference isCheck=" + newValue.toString());
				return false;
			}
		});
        
        mToggleButtonPrference.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				Log.e("hefeng", "mToggleButtonPrference isCheck=" + preference.toString());
				return false;
			}
		});
    }
}