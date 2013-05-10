package com.lyw.view;

import android.content.Context;
import android.view.View;
import android.widget.Scroller;

public class MyScrollView extends View {

	/**
	 * 滚动块
	 */
	private Scroller mScroller = null;
	/**
	 * 记录当前显示页
	 */
	private int mCurPageIdx = 0;
	
	public MyScrollView(Context context) {
		super(context);
		
		mScroller = new Scroller(context);
		mCurPageIdx = 0;
	}
}
