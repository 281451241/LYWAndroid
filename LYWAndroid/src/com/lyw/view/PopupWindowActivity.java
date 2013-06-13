package com.lyw.view;

import com.file.service.R;
import com.util.P;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopupWindowActivity extends Activity implements
		OnFocusChangeListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mLayout = new LinearLayout(this);
		mLayout.setOrientation(LinearLayout.VERTICAL);

		mEditText = new EditText(this);
		mEditText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		mEditText.setOnFocusChangeListener(this);
		mEditText2 = new EditText(PopupWindowActivity.this);
		mEditText2.addTextChangedListener(new TextWatcher()
		{
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				System.out.println("onTextChanged");
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				System.out.println("beforeTextChanged");				
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				System.out.println("afterTextChanged");	
				mTextView.setText(mEditText2.getText().toString());
			}
		});
		mEditText2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		mEditText2.setOnFocusChangeListener(mOnFocusChangeListener);

		mLayout.addView(mEditText);
		mLayout.addView(mEditText2);

		setContentView(mLayout);
	}

	private void initPopWindows()
	{
		mPopWindow = new PopupWindow(this);
		TextView tv = new TextView(this);
		tv.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		tv.setText("aaaaaaaaaaaa");
		mPopWindow.setContentView(tv);
	}

	private void initPopWindow()
	{
		if (mPopWindow == null)
		{
			mTextView = new TextView(this);
			mTextView.setText("aaaaaaaaaaaa");
			mTextView.setBackgroundColor(Color.WHITE);
			mPopWindow = new PopupWindow(mTextView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		if (mPopWindow.isShowing())
		{
			mPopWindow.dismiss();
		}
	}

	private LinearLayout mLayout = null;
	private PopupWindow mPopWindow = null;
	private EditText mEditText = null;
	private EditText mEditText2 = null;
	private TextView mTextView = null;

	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		if (hasFocus)
		{
			initPopWindow();
			mPopWindow.showAsDropDown(mEditText);
		}
		else
			mPopWindow.dismiss();
	}

	OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener()
	{

		@Override
		public void onFocusChange(View v, boolean hasFocus)
		{
			P.print("FocusChange");
			// if (hasFocus)
			initPopWindow();
			mPopWindow.showAtLocation(v, Gravity.LEFT | Gravity.TOP, 10, 400);
			// else
			// mPopWindow.dismiss();
		}
	};
}
