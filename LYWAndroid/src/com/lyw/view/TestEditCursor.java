package com.lyw.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TestEditCursor extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		mEdit = new EditText(this);
		layout.addView(mEdit);
		
		mBtn = new Button(this);
		mBtn.setText("I will move the cursor");
		mBtn.setOnClickListener(mBtnOnClickListener);
		layout.addView(mBtn);
		
		mBtn1 = new Button(this);
		mBtn1.setText("I will move the cursor");
		mBtn1.setOnClickListener(mBtn1OnClickListener);
		layout.addView(mBtn1);
		
		setContentView(layout);
	}
	
	
	
	LinearLayout layout;
	EditText mEdit;
	Button mBtn;
	Button mBtn1;
	
	OnClickListener mBtnOnClickListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			mEdit.extendSelection(mEdit.getText().length() - 1);
		}
	};
	OnClickListener mBtn1OnClickListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			mEdit.setSelection(mEdit.getText().length() - 1);
		}
	};
}
