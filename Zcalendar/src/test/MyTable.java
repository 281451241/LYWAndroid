package test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyTable extends View {

	private final static String[] WEEK_DATE = { "日", "一", "二", "三", "四", "五",
			"六" };
	private final static String[] NUM_CLASS = { "1", "3", "5", "7", "9", "11",
			"13" };
	private final static String tag = "MyTable";

	public MyTable(Context context) {
		this(context, null, 0);
		System.out.println("1");
	}

	public MyTable(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		System.out.println("2");
	}

	public MyTable(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		System.out.println("3");

		mPaint = new Paint();
		mCurButton = new Rect(0, 0, 0, 0);
		// setScrollContainer(true);
		setOnTouchListener(mTouchListener);
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);

		grid_width = dm.widthPixels / 8;

		mTextColor = Color.WHITE;
		mTextSize = 24;
	}
	
	public void redraw() {
		mIsOnClick = false;
		invalidate();
	}

	@Override
	public void onDraw(Canvas canvas) {
		mPaint.setColor(Color.rgb(98, 98, 98));
		mPaint.setStrokeWidth(2);
		mPaint.setStyle(Style.STROKE);
		mPaint.setAntiAlias(true);
		for (int i = 0; i < KeyCode.GRID_SIZE; i++) {
			for (int j = 0; j < KeyCode.GRID_SIZE; j++) {
				int mLeft = i * grid_width + mStartX;
				int mTop = j * grid_width + mStartY;
				int mRright = mLeft + grid_width;
				int mBottom = mTop + grid_width;
				canvas.drawRect(mLeft, mTop, mRright, mBottom, mPaint);
			}
		}

		mPaint.setStyle(Style.FILL);
		mPaint.setColor(mTextColor);
		mPaint.setTextSize(mTextSize);
		mPaint.setStrokeWidth(1);
		for (int i = 0, n = WEEK_DATE.length; i < n; i++) {
			float label_length = mPaint.measureText(WEEK_DATE[i]);
			// 为了居中文字
			canvas.drawText(WEEK_DATE[i], (grid_width - label_length) / 2
					+ grid_width * (i + 1), (grid_width + label_length) / 2,
					mPaint);
		}

		for (int i = 0, n = NUM_CLASS.length; i < n; i++) {
			float label_length = mPaint.measureText(NUM_CLASS[i]);
			// 为了居中文字
			canvas.drawText(NUM_CLASS[i], (grid_width - label_length) / 2,
					(grid_width + label_length) / 2 + grid_width * (i + 1),
					mPaint);
		}

		if (mIsOnClick) {
			System.out.println(mCurButton.left + ", " + mCurButton.top);
			mPaint.setColor(Color.GREEN);
			canvas.drawRect(mCurButton, mPaint);

		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(grid_width * 8, grid_width * 8);
	}

	class KeyCode {
		public static final int GRID_SIZE = 8;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		Log.d(tag, "onScrollChanged");
		mIsOnClick = false;
		invalidate();
		super.onScrollChanged(l, t, oldl, oldt);
	}

	private OnTouchListener mTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_DOWN) {

				mCurOnClickX = (int) (event.getX() / grid_width);
				mCurOnClickY = (int) (event.getY() / grid_width);

				Log.d(tag, "ACTION_DOWN: " + mCurOnClickX + "," + mCurOnClickY);

				mCurButton = new Rect(mCurOnClickX * grid_width, mCurOnClickY
						* grid_width, (mCurOnClickX + 1) * grid_width,
						(mCurOnClickY + 1) * grid_width);
				mIsOnClick = true;
				invalidate();
				return true;
			}
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				mCurOnClickX = (int) (event.getX() / grid_width);
				mCurOnClickY = (int) (event.getY() / grid_width);

				Log.d(tag, "ACTION_DOWN: " + mCurOnClickX + "," + mCurOnClickY);

				mCurButton = new Rect(mCurOnClickX * grid_width, mCurOnClickY
						* grid_width, (mCurOnClickX + 1) * grid_width,
						(mCurOnClickY + 1) * grid_width);
				mIsOnClick = true;
				invalidate();
				return true;
			}
			if (event.getAction() == MotionEvent.ACTION_UP) {

				mIsOnClick = false;
				invalidate();

			}
			
			return false;
		}

	};
	
	public boolean mIsOnClick;
	private Paint mPaint;
	private int grid_width;
	private int mStartX;
	private int mStartY;
	private Rect mCurButton;
	private int mCurOnClickX;
	private int mCurOnClickY;
	private int mTextColor;
	private float mTextSize;
}
