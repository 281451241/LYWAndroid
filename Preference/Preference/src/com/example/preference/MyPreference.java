package com.example.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPreference extends Preference
{
	private static final String TAG = "MyPreference";
	Drawable drawable;
	String string;
	CheckBox cb;
	public Button bt;
	boolean ischeck;

	public MyPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ThisStyle);
		drawable = a.getDrawable(R.styleable.ThisStyle_img);
		string = a.getString(R.styleable.ThisStyle_title);
		ischeck = a.getBoolean(R.styleable.ThisStyle_ischeck, false);
		a.recycle();
	}
	
	@Override
	protected void onAttachedToActivity()
	{
		super.onAttachedToActivity();
		
		Log.d(TAG, "onAttachedToActivity");
	}
	
	@Override
	protected void onAttachedToHierarchy(PreferenceManager preferenceManager)
	{
		super.onAttachedToHierarchy(preferenceManager);
		
		Log.d(TAG, "onAttachedToHierarchy");
	}
	
	protected void onBindView(View view)
	{
		super.onBindView(view);
		view.setBackgroundResource(android.R.drawable.list_selector_background);
		TextView tv = (TextView) view.findViewById(R.id.tv);
		tv.setText(string);
		
		bt = (Button) view.findViewById(R.id.bt);
//		bt.setBackgroundResource(android.R.drawable.btn_default);
		bt.setFocusable(false);
		bt.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				System.out.println("Button onclick!!!!");
			}
		});
//		
//		bt.setClickable(true);
		
		cb = (CheckBox) view.findViewById(R.id.cb);
		if (ischeck)
		{
			cb.setChecked(true);
			changeData(true);
		}
		else
		{
			cb.setChecked(false);
			changeData(false);
		}

	}
	
	@Override
	protected void onClick()
	{
		super.onClick();
		System.out.println("onClick");
		cb.setChecked(!cb.isChecked());
	}
	
	@Override
	protected Object onGetDefaultValue(TypedArray a, int index)
	{
		return super.onGetDefaultValue(a, index);
	}

	protected View onCreateView(ViewGroup parent)
	{
		View view = LayoutInflater.from(getContext()).inflate(R.layout.layout,
				parent, false);
		return view;
	}

	public void changeData(boolean isUpdate)
	{
		SharedPreferences.Editor editor = getEditor();
		editor.putBoolean(getKey(), isUpdate);
		editor.commit();
	}

}