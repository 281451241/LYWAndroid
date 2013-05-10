package com.lyw.view;

import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class LYWCandViewAddBlur extends View {

	@TargetApi(Build.VERSION_CODES.CUPCAKE)
	public LYWCandViewAddBlur(Context context) {
		super(context);
		mContext = context;
		mScroller = new Scroller(context);
		
		setDrawingCacheBackgroundColor(Color.BLACK);
		setHorizontalFadingEdgeEnabled(true);
		setHorizontalScrollBarEnabled(true);
		setFadingEdgeLength(10);

		mCurScreen = mDefaultScreen;
	}

	// ////////////////////////////////////////////////////////////////////
	//
	// ////////////////////////////////////////////////////////////////////
	private static final String TAG = "ScrollLayout";
	private Scroller mScroller;

	private VelocityTracker mVelocityTracker;
	private int mCurScreen;
	private int mDefaultScreen = 0;

	private static final int SNAP_VELOCITY = 600;


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

	/**
	 * 
	 * @param padding
	 */
	public final void setHorContentPadding(int padding) {
		mHorContentPadding = notLessThenZero(padding);
	}

	/**
	 * 
	 * @param padding
	 */
	public final void setVerContentPadding(int padding) {
		mVerContentPadding = notLessThenZero(padding);
	}

	/**
	 * 设置项目分隔条宽度，0表示不使用分隔条
	 * 
	 * @param width
	 */
	public final void setItemSeparatorWidth(int width) {
		mItemSeparatorWidth = notLessThenZero(width);
	}

	/**
	 * 设置控件分隔条高度，0表示不使用
	 * 
	 * @param height
	 */
	public final void setCtrlSeparatorHeight(int height) {
		mCtrlSeparatorHeight = notLessThenZero(height);
	}

	/**
	 * 设置候选项目内容水平衬垫
	 * 
	 * @param padding
	 */
	public final void setCandItemHorContentPadding(int padding) {
		mCandItemHorContentPadding = notLessThenZero(padding);
	}

	/**
	 * 设置候选项目内容垂直衬垫
	 * 
	 * @param padding
	 */
	public final void setCandItemVerContentPadding(int padding) {
		mCandItemVerContentPadding = notLessThenZero(padding);
	}

	/**
	 * 设置候选项目文本大小
	 * 
	 * @param fontSize
	 */
	public final void setCandItemFontSize(float fontSize) {
		mCandItemFontSize = notLessThenZero(fontSize);
	}

	/**
	 * 设置候选项目画笔
	 * 
	 * @param textPaint
	 */
	public final void setCandItemTextPaint(Paint textPaint) {
		mCandItemTextPaint = null;
		mCandItemTextPaint = textPaint;
	}

	/**
	 * 设置是否允许绘制上分隔条
	 * 
	 * @param on
	 */
	public final void enableDrawTopCtrlSeparator(boolean on) {
		mEnableDrawTopCtrlSeparator = on;
	}

	/**
	 * 设置是否允许绘制下分隔条
	 * 
	 * @param on
	 */
	public final void enableDrawBottomCtrlSeparator(boolean on) {
		mEnableDrawBottomCtrlSeparator = on;
	}

	/**
	 * 获取最大可容纳候选项目的宽度
	 * 
	 * @return
	 */
	public final int getMaxFreeWidth() {
		final int contentWidth = notLessThenZero(mWidth - 2
				* mHorContentPadding);
		// 记录当前可用宽度
		int curFreeWidth = contentWidth;

		return curFreeWidth;
	}

	// ////////////////////////////////////////////////////////////////////
	//
	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////
	//
	// ////////////////////////////////////////////////////////////////////


	/**
	 * 获取项目分隔条宽度
	 * 
	 * @return
	 */
	public final int getItemSeparatorWidth() {
		return mItemSeparatorWidth;
	}

	// ////////////////////////////////////////////////////////////////////
	// Override methods from super class
	// ////////////////////////////////////////////////////////////////////

	@SuppressLint("NewApi")
	@Override
	protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int width = MeasureSpec.getSize(widthMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"ScrollLayout only canmCurScreen run at EXACTLY mode!");
		}

		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"ScrollLayout only can run at EXACTLY mode!");
		}

		System.out.println("moving to screen " + mCurScreen);
		scrollTo(mCurScreen * width, 0);
	}

	@Override
	protected final void onDraw(Canvas canvas) {

		// 绘制当前页项目
		// ...
		boolean isFirstKey = true;
		int keySpLeft = 0;
		final int keySpTop = mVerContentPadding;
		final int keySpHeight = notLessThenZero(mHeight - 2
				* mVerContentPadding);
		int j = 0;

		TextView tv = new TextView(mContext);
		
		tv.setText("aaaaaaaaaaaaaaa");
//		canvas.translate(l, t);
		
	}

	// ////////////////////////////////////////////////////////////////////
	// Private Helpers
	// ////////////////////////////////////////////////////////////////////

	private final int notLessThenZero(int val) {
		return (val < 0) ? 0 : val;
	}

	private final float notLessThenZero(float val) {
		return (val < 0.0f) ? 0.0f : val;
	}

	private final void drawSp(Canvas canvas, Drawable drawable, int l,
			int t, int w, int h) {
		if (w <= 0 && h <= 0)
			return;
		TextView tv = new TextView(mContext);
		tv.setText("hello");
		tv.setBackgroundColor(Color.GRAY);
//		tv.setBackgroundResource(R.drawable.blur_left);
//		tv.setLayoutParams(new FrameLayout.LayoutParams(w,70));
		drawable = tv.getBackground();
		if (drawable != null) {
			drawable.setBounds(0, 0, w, h);
			canvas.translate(l, t);
//			tv.draw(canvas);
			drawable.draw(canvas);
			canvas.translate(0 - l, 0 - t);
		}
	}

	private final void drawBlur(Canvas canvas, Drawable sp,
			int l, int t, int w, int h) {
		TextView tv = new TextView(mContext);
		tv.setText("hello");
		tv.setBackgroundColor(Color.GRAY);
//		tv.setBackgroundResource(R.drawable.blur_left);
//		if (w <= 0 && h <= 0)
//			return;
		tv.setLayoutParams(new FrameLayout.LayoutParams(20,70));
		canvas.translate(l, t);
		tv.draw(canvas);
		canvas.translate(0 - l, 0 - t);
	}

	// ////////////////////////////////////////////////////////////////////
	// Implements other interfaces
	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////
	// Private Members
	// ////////////////////////////////////////////////////////////////////

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
	private int mPageKeyWidth = 0;
	/**
* 
*/
	private int mItemSeparatorWidth = 0;
	/**
* 
*/
	private int mCtrlSeparatorHeight = 0;
	/**
* 
*/
	private int mCandItemHorContentPadding = 0;
	/**
* 
*/
	private int mCandItemVerContentPadding = 0;
	/**
* 
*/
	private int mHorContentPadding = 0;
	/**
* 
*/
	private int mVerContentPadding = 0;
	/**
* 
*/
	private float mCandItemFontSize = 0.0f;
	/**
* 
*/
	private Paint mCandItemTextPaint = null;
	/**
* 
*/
	private boolean mEnableDrawTopCtrlSeparator = false;
	/**
* 
*/
	private boolean mEnableDrawBottomCtrlSeparator = false;
	/**
* 
*/
	/**
	 * 当前参与工作按键序列，包括翻页按键和候选项目
	 */
	private LinkedList<Integer> mList = null;
	/**
	 * 是否开启分散对齐
	 */
	private boolean mEnableTileAlign = true;

	public void snapToDestination() {
		final int destScreen = (getScrollX() + mWidth / 2) / mWidth;
		snapToScreen(destScreen);
	}

	public void snapToScreen(int whichScreen) {
		// get the valid layout page
		whichScreen = Math.max(0, Math.min(whichScreen, TOTAL_PAGE - 1));
		if (getScrollX() != (whichScreen * mWidth)) {

			final int delta = whichScreen * mWidth - getScrollX();
			mScroller.startScroll(getScrollX(), 0, delta, 0, 200);
			mCurScreen = whichScreen;
			invalidate(); // Redraw the layout
		}
		if (mCurScreen + 1 <= TOTAL_PAGE) {
		}
	}

	public void setToScreen(int whichScreen) {
		whichScreen = Math.max(0, Math.min(whichScreen, TOTAL_PAGE - 1));
		mCurScreen = whichScreen;
		snapToScreen(mCurScreen);
	}

	public int getCurScreen() {
		return mCurScreen;
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

	private static int TOTAL_PAGE = 0;
	Context mContext;
}
