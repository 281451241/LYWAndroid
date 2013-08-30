package test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.View;

import com.fairy.wuziqi.uilts.KeyCode;

public class MyTable extends View {
	
	private int grid_width;
	private int mStartX;
	private int mStartY;
	private int mTextColor;
	private float mTextSize;

	public MyTable(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		KeyCode.GRID_SIZE = 8;

		DisplayMetrics dm = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		grid_width = dm.widthPixels / 8;
		
		mTextColor = Color.WHITE;
		mTextSize = 24 ;
	}

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.rgb(98,98, 98));
		paint.setStrokeWidth(2);
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		for (int i = 0; i < KeyCode.GRID_SIZE; i++) {
			for (int j = 0; j < KeyCode.GRID_SIZE; j++) {
				int mLeft = i * grid_width + mStartX;
				int mTop = j * grid_width + mStartY;
				int mRright = mLeft + grid_width;
				int mBottom = mTop + grid_width;
				canvas.drawRect(mLeft, mTop, mRright, mBottom, paint);
			}
		}
		
		paint.setStyle(Style.FILL);
		paint.setColor(mTextColor);
		paint.setTextSize(mTextSize);
		paint.setStrokeWidth(1);
		for (int i = 0, n=WEEK_DATE.length; i < n; i++) {
			float label_length = paint.measureText(WEEK_DATE[i]);
			// 为了居中文字
			System.out.println("i: " + i);
			canvas.drawText(WEEK_DATE[i],
					(grid_width - label_length) / 2 + grid_width * (i+1),
					(grid_width + label_length) / 2, paint);
		}
		
		
		for (int i = 0, n=NUM_CLASS.length; i < n; i++) {
			float label_length = paint.measureText(NUM_CLASS[i]);
			// 为了居中文字
			System.out.println("i: " + i);
			canvas.drawText(NUM_CLASS[i],
					(grid_width - label_length) / 2,
					(grid_width + label_length) / 2 + grid_width * (i+1),
					paint);
		}
	}
	
	private final static String[] WEEK_DATE = {"日", "一", "二", "三", "四", "五", "六"}; 
	private final static String[] NUM_CLASS = {"1", "3", "5", "7", "9", "11", "13"}; 
}
