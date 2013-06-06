package com.lyw.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.file.service.R;

public class TAnim extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new TAnimView(this));
	}

}

class TAnimView extends ViewGroup
{

	public TAnimView(Context context)
	{
		super(context);

		mContext = context;
		temp = mW;
		initTV();
		addView(tv, 0);
		initBT();
		addView(bt, 1);
		setBackgroundColor(Color.WHITE);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(mW, mH);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		canvas.translate(50, 50);
		// tv.draw(canvas);
		canvas.translate(-50, -50);

		Drawable drawable = getResources().getDrawable(R.drawable.blur_right);

		drawable.setBounds(0, 0, 200, 100);
		canvas.translate(100, 100);
		drawable.draw(canvas);
		canvas.translate(-100, -100);

		// bt.draw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom)
	{
		tv.layout(50, 50, 200, 100);
		bt.layout(0, 0, 100, 50);
	}

	@Override
	protected void onAnimationEnd()
	{
		super.onAnimationEnd();

		System.out.println("aaaaaaaaaaaaaaaa");
	}

	private void initTV()
	{
		tv = new TextView(mContext);
		tv.setText("aaaaaaaaaaaaaaaaaaa");
		tv.setBackgroundColor(Color.BLUE);

		// tv.setGravity(Gravity.CENTER);
	}

	private void initBT()
	{
		bt = new Button(mContext);
		bt.setText("aaaa");
		bt.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				tv.setAnimation(getAnim());
			}
		});
		// tv.setGravity(Gravity.CENTER);
	}

	private Animation getAnim()
	{
		temp = -temp;
		Animation anim = new TranslateAnimation(0, temp, 0, 0);
		anim.setDuration(1000);
		anim.setAnimationListener(new AnimationListener()
		{
			
			@Override
			public void onAnimationStart(Animation animation)
			{
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation)
			{
				tv.layout(tv.getLeft()+temp, tv.getTop(), tv.getRight()+temp, tv.getBottom());
			}
		});
		return anim;
	}

	
	Button bt;
	TextView tv;

	boolean flag = false;
	Context mContext;
	public int mW = 480;
	int temp;
	public int mH = 600;
}
