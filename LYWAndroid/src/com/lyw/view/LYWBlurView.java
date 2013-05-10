package com.lyw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

public class LYWBlurView extends View {

	Paint paint;
	public LYWBlurView(Context context) {
		super(context);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		
	}
	
	public LYWBlurView(Context context, int width, int height, String dir) {
		this(context);
		
		mWidth = width;
		mHeight = height;
		mDirection = dir;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Shader mShader;
		int startX = 0;
		int endX = 0;
		int mColor[] = null;
		
		canvas.drawColor(Color.parseColor("#00000000"));

		if (mDirection.equals("left")) {
			startX = -30;
			endX = 20;
			mColor = new int[] { Color.BLACK, Color.parseColor("#00000000") };
		} else if (mDirection.equals("right")) {
			startX = 0;
			endX = 50;
			mColor = new int[] { Color.parseColor("#00000000"), Color.BLACK };
		} else {
			return;
		}
		mShader = new LinearGradient(startX, 0, endX, 0, mColor,
				null, Shader.TileMode.REPEAT);
		
		paint.setShader(mShader);

		canvas.drawRect(0, 0, mWidth, mHeight, paint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mWidth, mHeight);
	}

	/**
	 * 
	 * @param width
	 */
	public final void setWidth(int width) {
		mWidth = defaultValue(width);
	}

	/**
	 * 
	 * @param height
	 */
	public final void setHeight(int height) {
		mHeight = defaultValue(height);
	}

	public final void setDirection(String direction) {
		mDirection = defaultValue(direction);
	}
	
	private final int defaultValue(int val) {
		return (val <= 0) ? 50 : val;
	}
	private final String defaultValue(String direction) {
		return ("".equals(direction)) ? "left" : direction;
	}

	/**
	 * 本画布宽度
	 */
	private int mWidth = 50;
	/**
	 * 本画布高度
	 */
	private int mHeight = 50;
	/**
	 * 渐变效果的方向,允许为"left"和"right"
	 */
	private String mDirection = "left";
}
