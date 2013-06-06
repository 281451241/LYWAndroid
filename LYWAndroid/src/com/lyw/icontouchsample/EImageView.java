package com.lyw.icontouchsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class EImageView extends ImageView
{

	private static final String TAG = "ETextView";
	private static final int STATE_STOP = 0;
	private static final int STATE_MOVE = 1;
	private int mState;
	private int mPreviousx = 0;
	private int mPreviousy = 0;
	private int mPreviousBox = -1;

	int mCurBox = -1;

	private ViewBox viewBox;
	private DragController dragController;

	// Initial the view.
	public EImageView(Context context, AttributeSet attribute)
	{
		this(context, attribute, 0);
	}

	// Initial the view.
	public EImageView(Context context, AttributeSet attribute, int style)
	{
		super(context, attribute, style);
		viewBox = new ViewBox();
		dragController = new DragController(Main.layout);
		mState = STATE_STOP;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(viewBox.getBoxWidth(), viewBox.getBoxHight());
	}

	// On touch Event.
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		final int iAction = event.getAction();
		final int iCurrentx = (int) event.getX();
		final int iCurrenty = (int) event.getY();
		switch (iAction)
		{
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "ACTION_DOWN");
			mState = STATE_MOVE;
			mPreviousx = iCurrentx;
			mPreviousy = iCurrenty;
			mPreviousBox = viewBox.whichBox(this);
			Main.boxList[mPreviousBox] = false;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "ACTION_MOVE");
			mState = STATE_MOVE;
			int iDeltx = iCurrentx - mPreviousx;
			int iDelty = iCurrenty - mPreviousy;
			final int iLeft = getLeft();
			final int iTop = getTop();
			layout(iLeft + iDeltx, iTop + iDelty, iLeft + iDeltx + getWidth(),
					iTop + iDelty + getHeight());
			dragController.startDrag(this);
			mPreviousx = iCurrentx - iDeltx;
			mPreviousy = iCurrenty - iDelty;
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "ACTION_UP");

			int iDeltx1 = iCurrentx - mPreviousx;
			int iDelty1 = iCurrenty - mPreviousy;
			mCurBox = viewBox.whichBox(this);
			final int iLeft1 = viewBox.getLeft(mCurBox);
			final int iTop1 = viewBox.getTop(mCurBox);
			layout(iLeft1 + iDeltx1, iTop1 + iDelty1, iLeft1 + iDeltx1
					+ getWidth(), iTop1 + iDelty1 + getHeight());
			mPreviousx = iCurrentx - iDeltx1;
			mPreviousy = iCurrenty - iDelty1;
			Main.boxList[mCurBox] = true;
			mState = STATE_STOP;
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.e(TAG, "ACTION_CANCEL");
			mState = STATE_STOP;
			break;
		}
		return true;
	}

	private boolean isOverlap()
	{
		return Main.boxList[viewBox.whichBox(this)];
	}

	public void reLayout(int which)
	{
		layout(viewBox.getLeft(which), viewBox.getTop(which),
				viewBox.getLeft(which) + getWidth(), viewBox.getTop(which)
						+ getHeight());
	}

	public int getCurIndex()
	{
		return mCurBox;
	}
}
