package com.lyw.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WGIMPopupInputBarActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		LinearLayout mLinearLayout = new LinearLayout(this);
		mLinearLayout.setOrientation(LinearLayout.VERTICAL);
		
		initPopupWindow();
		
		mEdit = new EditText(this);
		mLinearLayout.addView(mEdit);

		TextView t = new TextView(this);
		t.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		mLinearLayout.addView(t);
		
		mButton = new Button(this);
		mButton.setOnClickListener(mButtonOnClickListener);
		mLinearLayout.addView(mButton);
		
		
		setContentView(mLinearLayout);
	}
	WGIMPopupInputBar mInputBar;
	EditText mEdit;
	Button mButton;
	private void initPopupWindow() {
		mInputBar = new WGIMPopupInputBar(this, -1, 50);
		mInputBar.setOnTextViewClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				System.out.println("TextView onclick!");
				if(v instanceof TextView)
					System.out.println(((TextView) v).getText().toString());
			}
		});
	}
	
	private OnClickListener mButtonOnClickListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			System.out.println("mButtonOnClick");
			String str = mEdit.getText().toString();
			mInputBar.setText(str);
			if(mInputBar.isShowing())
				mInputBar.updatePopupWindow(mButton, 0, 0, str);
			else
				mInputBar.showAsDropDown(str, mButton, 0, 0);
		}
	};
	
}
