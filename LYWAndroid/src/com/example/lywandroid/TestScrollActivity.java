package com.example.lywandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class TestScrollActivity extends Activity {

	private TextView tv;
	private Scroller mScroller;
	LinearLayout linearLayout = null;
	private VelocityTracker mVelocityTracker;

	private int mCurScreen;
	private int mDefaultScreen = 0;

	private static final int TOUCH_STATE_REST = 0;
	private static final int TOUCH_STATE_SCROLLING = 1;

	private static final int SNAP_VELOCITY = 600;

	private int mTouchState = TOUCH_STATE_REST;
	private int mTouchSlop;
	private float mLastMotionX;
	private float mLastMotionY;

	HorizontalScrollView scrollView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		scrollView = new HorizontalScrollView(this);
		scrollView.setBackgroundColor(Color.WHITE);
		scrollView.setLayoutParams(new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 70, Gravity.CENTER));
		scrollView.setHorizontalFadingEdgeEnabled(true);
		scrollView.setFadingEdgeLength(20);

		mScroller = new Scroller(this);
		
		addView();

		scrollView.addView(linearLayout);
		scrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (mVelocityTracker == null) {
					mVelocityTracker = VelocityTracker.obtain();
				}
				mVelocityTracker.addMovement(event);

				final int action = event.getAction();
				final float x = event.getX();
				final float y = event.getY();

				switch (action) {
				case MotionEvent.ACTION_DOWN:
					// if (!mScroller.isFinished()) {
					// mScroller.abortAnimation();
					// }
					mLastMotionX = x;
					System.out.println("MotionEvent.ACTION_DOWN");
					break;

				case MotionEvent.ACTION_MOVE:
					int deltaX = (int) (mLastMotionX - x);
					mLastMotionX = x;
					System.out.println("MotionEvent.ACTION_MOVE");
					// scrollBy(deltaX, 0);
					break;

				case MotionEvent.ACTION_UP:

					// if (mTouchState == TOUCH_STATE_SCROLLING) {
					final VelocityTracker velocityTracker = mVelocityTracker;
					velocityTracker.computeCurrentVelocity(1000);
					int velocityX = (int) velocityTracker.getXVelocity();

					if (velocityX > SNAP_VELOCITY) {
						// if (velocityX > SNAP_VELOCITY && mCurScreen > 0) {
						// Fling enough to move left
						// onScreenChangeListener.onScreenChange(mCurScreen -
						// 1);
						// System.out.println("mCurScreen=" + (mCurScreen - 1));
						// snapToScreen(mCurScreen - 1);
						// scrollView.scrollTo(0, 0);
						mScroller.startScroll(480, 0, 0, 0, 1000);
						scrollView.invalidate();
//						scrollView.computeScroll();
						if (mScroller.computeScrollOffset()) {
							scrollView.scrollTo(mScroller.getCurrX(),
									mScroller.getCurrY());
							scrollView.postInvalidate();
						}
						System.out.println("Fling enough to move left");
					} else if (velocityX < -SNAP_VELOCITY) {
						// } else if (velocityX < -SNAP_VELOCITY
						// && mCurScreen < getChildCount() - 1) {
						// Fling enough to move right
						// onScreenChangeListener.onScreenChange(mCurScreen +
						// 1);
						// //只往右移动才加载数据
						// onScreenChangeListenerDataLoad.onScreenChange(mCurScreen+1);
						// snapToScreen(mCurScreen + 1);
						// scrollView.scrollTo(480, 0);
						addView(); 
//						scrollView.addView(linearLayout);
						mScroller.startScroll(0, 0, 480, 0, 1000);
						scrollView.invalidate();
//						scrollView.computeScroll();
						if (mScroller.computeScrollOffset()) {
							scrollView.scrollTo(mScroller.getCurrX(),
									mScroller.getCurrY());
							scrollView.postInvalidate();
						}
						System.out.println("Fling enough to move right");
					} else {
						// snapToDestination();
						System.out.println("else");
					}

					if (mVelocityTracker != null) {
						mVelocityTracker.recycle();
						mVelocityTracker = null;
					}
					// }
					mTouchState = TOUCH_STATE_REST;
					break;
				case MotionEvent.ACTION_CANCEL:
					mTouchState = TOUCH_STATE_REST;
					break;
				}

				return true;
			}
		});

		FrameLayout frameLayout = new FrameLayout(this);
		frameLayout.setLayoutParams(new FrameLayout.LayoutParams(480, 70));
		frameLayout.addView(scrollView);
		setContentView(frameLayout);
	}

	private void addView() {
		linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		for (int i = 0; i < 20; i++) {
			tv = new TextView(this);
			tv.setText("aaa" + i);
			linearLayout.addView(tv);
		}
	}

	public void snapToScreen(int startX, int endX) {

		mScroller.startScroll(startX, 0, endX, 0, 1000);
		scrollView.invalidate(); // Redraw the layout
	}
}
