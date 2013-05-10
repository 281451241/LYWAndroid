package com.example.lywandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.lyw.view.DoubleLineTextView;

public class TestDoubleLineActivity extends Activity {
	
	DoubleLineTextView mDoubleLineTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initDoubleLine();
		setContentView(mDoubleLineTextView);
	}

	private void initDoubleLine() {
		mDoubleLineTextView = new DoubleLineTextView(this);
		mDoubleLineTextView.setWidth(320);
		mDoubleLineTextView.setHeight(100);
		mDoubleLineTextView.setBackgroundColor(Color.WHITE);
		
		mDoubleLineTextView.setMainText("aaaaaaaaaaaaaaaaaaaa");
		mDoubleLineTextView.setLeftText("bbbbbbbbbb");
		mDoubleLineTextView.setRightText("cccccccccccccccccc");
		
		mDoubleLineTextView.initViews();
	}
}
