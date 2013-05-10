package com.lyw.view;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

public class DoubleLineTextView extends ViewGroup {

	public DoubleLineTextView(Context context) {
		super(context);
		mainText = new TextView(context);
		leftText = new TextView(context);
		rightText = new TextView(context);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		getChildAt(0).layout(0, 0, mWidth, (int) (mHeight * 0.6));
		getChildAt(1).layout(0, (int) (mHeight * 0.7), (int) (mWidth * 0.6), mHeight);
		getChildAt(2).layout((int) (mWidth * 0.7), (int) (mHeight * 0.7), mWidth, mHeight);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		setMeasuredDimension(mWidth, mHeight);
	}
	
	public void initViews() {
		addView(mainText);
		addView(leftText);
		addView(rightText);
	}

	public void setWidth(int width) {
		mWidth = notLessThenZero(width);
	}

	public void setHeight(int height) {
		mHeight = notLessThenZero(height);
	}
	
	public void setMainText(String str) {
		mainText.setText(str);
		mainText.setTextSize(32f);
		mainText.setTextColor(Color.BLUE);
	}
	public void setLeftText(String str) {
		leftText.setText(str);
		leftText.setTextSize(24f);
		leftText.setTextColor(Color.BLUE);
	}
	public void setRightText(String str) {
		rightText.setText(str);
		rightText.setTextSize(24f);
		rightText.setTextColor(Color.BLUE);
	}

	private final int notLessThenZero(int val) {
		return (val < 0) ? 0 : val;
	}

	private final float notLessThenZero(float val) {
		return (val < 0.0f) ? 0.0f : val;
	}

	private int mWidth;
	private int mHeight;
	private TextView mainText;
	private TextView leftText;
	private TextView rightText;

}
