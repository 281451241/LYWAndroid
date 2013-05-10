package com.example.lywandroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.file.service.R;
import com.lyw.view.LYWBlurView;
import com.lyw.view.LYWView;
import com.lyw.view.MirrorView;
import com.lyw.view.MyView;
import com.lyw.view.TestViewGroup;

public class MainActivity extends Activity {

	public static LYWView rightBlur;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		

		LYWBlurView blurView = new LYWBlurView(this);
		blurView.setWidth(20);
		blurView.setHeight(100);
		blurView.setDirection("right");
		
//		setContentView(blurView);
//		setContentView(new DefinedScrollView(this, null));
//		setContentView(R.layout.main);
		setContentView(new TestViewGroup(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private View get() {
		ImageView iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.test_keyboard_arrow_left_normal);
		
		Bitmap bm = iv.getDrawingCache();
		MirrorView mirrorView = new MirrorView(this, bm);
		
		return mirrorView;
	}
	
	private void f() {
		Paint p=new Paint();
		LinearGradient lg=new LinearGradient(0,0,100,100,Color.RED,Color.BLUE,Shader.TileMode.MIRROR);  
	}
	
	private void ff() {
		FrameLayout frameLayout = new FrameLayout(this);
		
		ImageView iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.blur);
		
		frameLayout.addView(iv);
		
//		setContentView(frameLayout);
		
		MyView myView = new MyView(this,null);
		
		
		rightBlur = new LYWView(this);
		rightBlur.setBackgroundColor(Color.BLUE);
//		rightBlur.setLefts(460);
		rightBlur.setWidth(480);
		rightBlur.setHeight(800);
		rightBlur.setResId(R.drawable.blur_right);
//		frameLayout.addView(myView);
	}

}
