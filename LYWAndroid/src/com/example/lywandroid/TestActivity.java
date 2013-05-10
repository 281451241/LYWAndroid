package com.example.lywandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.lyw.view.TestLayout;

public class TestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
//		FrameLayout f = new FrameLayout(this);
//		f.setBackgroundColor(Color.GREEN);
		
		TestLayout tl = new TestLayout(this);
		tl.setBackgroundColor(Color.BLACK);
		tl.setLayoutParams(new TestLayout.LayoutParams(400,100));
		
//		f.addView(tl);
		setContentView(tl);
	}
}
