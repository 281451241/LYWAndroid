package com.lyw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class LYWView extends View {

	public LYWView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getResources().getDrawable(mResId);
		
		drawable.setBounds(0, 0, 200, 100);
		canvas.translate(200, 100);
		drawable.draw(canvas);
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
		mWidth = notLessThenZero(width);
	}

	/**
	 * 
	 * @param height
	 */
	public final void setHeight(int height) {
		mHeight = notLessThenZero(height);
	}
	
	public final void setResId(int resId) {
		mResId = resId;
	}
	
//	public final void setLefts(int left) {
//		mLeft = left;
//	}
	
	private final int notLessThenZero(int val) {
		return (val < 0) ? 0 : val;
	}
	
	/**
	 * 
	 */
	private int mWidth = 0;
	/**
	 * 
	 */
	private int mHeight = 0;
	/**
	 * 
	 */
	private int mResId = 0;
	/**
	 * 
	 */
	private int mLeft = 0;

}
