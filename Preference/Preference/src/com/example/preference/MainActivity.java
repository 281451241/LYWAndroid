package com.example.preference;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends PreferenceActivity
{
	boolean isCheck = true;

	Button bt;

	MyPreference store, notification;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		store = (MyPreference) findPreference("storeAfter");
		notification = (MyPreference) findPreference("notification");
		store.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			public boolean onPreferenceClick(Preference preference)
			{
				boolean temp = getStoreValue(getApplicationContext());
				if (temp)
				{
					store.changeData(false);
					Toast.makeText(getApplicationContext(), "false",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "true",
							Toast.LENGTH_SHORT).show();
					store.changeData(true);
				}

				return true;
			}
		});
	}

	public static boolean getStoreValue(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean("storeAfter", false);

	}

	public static boolean getNotificationValue(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean("notification", false);

	}

}