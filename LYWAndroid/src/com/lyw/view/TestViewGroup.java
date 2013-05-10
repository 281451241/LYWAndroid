package com.lyw.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestViewGroup extends ViewGroup {

	private static final String TAG = "TestViewGroup";

	public TestViewGroup(Context context) {
		super(context);
		TextView tv = new TextView(context);
		tv.setText("aaaaaaaaaaaaaa");

		Button bt = new Button(context);
		bt.setText("bbbbbbbbbbbb");

		Button bt1 = new Button(context);
		bt1.setText("aaaaaaaaaaaaaa");

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.addView(tv);
		layout.addView(bt);
		layout.addView(bt1);

		HorizontalScrollView horizontalScrollView = new HorizontalScrollView(
				context);
		horizontalScrollView.addView(layout);

		addView(horizontalScrollView);
	}

	@Override
	// 对每个子View进行measure():设置每子View的大小，即实际宽和高
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 通过init()方法，我们为该ViewGroup对象添加了三个视图 ， Button、 ImageView、TextView
		// widthMeasureSpec = 480;
		int childCount = getChildCount();
		Log.i(TAG, "the size of this ViewGroup is ----> " + childCount);

		Log.i(TAG, "**** onMeasure start *****");

		// 获取该ViewGroup的实际长和宽 涉及到MeasureSpec类的使用
		int specSize_Widht = MeasureSpec.getSize(widthMeasureSpec);
		int specSize_Heigth = MeasureSpec.getSize(heightMeasureSpec);

		Log.i(TAG, "**** specSize_Widht " + specSize_Widht
				+ " * specSize_Heigth   *****" + specSize_Heigth);

		// 设置本ViewGroup的宽高
		setMeasuredDimension(specSize_Widht, specSize_Heigth);

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i); // 获得每个对象的引用
			child.measure(100, 70); // 简单的设置每个子View对象的宽高为 50px , 50px
			// 或者可以调用ViewGroup父类方法measureChild()或者measureChildWithMargins()方法
			// this.measureChild(child, widthMeasureSpec, heightMeasureSpec) ;
		}

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int childCount = getChildCount();

		int startLeft = 0;// 设置每个子View的起始横坐标
		int startTop = 10; // 每个子View距离父视图的位置 ， 简单设置为10px吧 。 可以理解为
							// android:margin=10px ;

		Log.i(TAG, "**** onLayout start ****");
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i); // 获得每个对象的引用
			child.layout(startLeft, startTop,
					startLeft + child.getMeasuredWidth(),
					startTop + child.getMeasuredHeight());
			startLeft = startLeft + child.getMeasuredWidth() + 10; // 校准startLeft值，View之间的间距设为10px
																	// ;
			Log.i(TAG, "**** onLayout startLeft ****" + startLeft);
		}
	}

}
