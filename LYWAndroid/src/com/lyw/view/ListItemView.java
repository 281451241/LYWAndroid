package com.lyw.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

public class ListItemView extends View
{

	public ListItemView(Context context)
	{
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		setMeasuredDimension(mWidth, mHeight);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom)
	{
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		
		ImageView im = new ImageView(getContext());
		im.setBackgroundColor(Color.BLUE);
	}
	
	public void setWidth(int w) {
		mWidth = w;
	}
	
	public void setHeight(int h) {
		mHeight = h;
	}
	
	

	private int mWidth;
	private int mHeight;
	
}
