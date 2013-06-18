package com.lyw.icontouchsample;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;

import com.file.service.R;

public class Main extends Activity implements OnLongClickListener
{
	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;

	private int mode = NONE;
	private float oldDist;
	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	private PointF start = new PointF();
	private PointF mid = new PointF();

	public static boolean boxList[] = new boolean[16]; // 记录哪些位置已有图标,哪些没有图标
	public static LinearLayout layout;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getDimension();

		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
//		layout.setOnLongClickListener(this);
		EImageView eImageView1 = new EImageView(this, null);
		eImageView1.setImageResource(R.drawable.blur);
		addView(eImageView1);
		eImageView1.setOnLongClickListener(this);
		EImageView eImageView2 = new EImageView(this, null);
		eImageView2.setImageResource(R.drawable.ic_launcher);
		addView(eImageView2);
		eImageView2.setOnLongClickListener(this);
		EImageView eImageView3 = new EImageView(this, null);
		eImageView3
				.setImageResource(R.drawable.test_keyboard_arrow_left_normal);
		addView(eImageView3);
		eImageView3.setOnLongClickListener(this);

		setContentView(layout);
	}

	int i = 0;

	private void addView(EImageView view)
	{
		layout.addView(view);
		view.mCurBox = i;
		boxList[i] = true;
		i++;
	}

	public boolean onLongClick(View v)
	{
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		if (v instanceof EImageView)
		{
			// User long pressed on an item
//			mWorkspace.startDrag(longClickCellInfo);
		}
		return true;
	}

	private void getDimension()
	{
		/** 获取屏幕的宽和高 */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		SCREEN_WIDTH = dm.widthPixels;
		SCREEN_HEIGHT = dm.heightPixels;
	}

	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;
}