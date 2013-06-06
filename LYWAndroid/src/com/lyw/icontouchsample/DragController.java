package com.lyw.icontouchsample;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DragController
{
	ViewBox viewBox = new ViewBox();
	ViewGroup vGroup;

	public DragController(LinearLayout layout)
	{
		vGroup = layout;
	}

	private boolean isOverlap(View v)
	{
		return Main.boxList[viewBox.whichBox(v)];
	}

	EImageView eV;

	private EImageView findOverView(int which)
	{
		int viewCount = vGroup.getChildCount();
		for (int i = 0; i < viewCount; i++)
		{
			View v = vGroup.getChildAt(i);
			if (v instanceof EImageView)
				eV = (EImageView) v;
			if (eV.mCurBox == which)
				return eV;
		}
		return null;
	}

	private int findEmptyPlace(int curL, int curT, int perL, int perT, int which)
	{
		float spanX = 0;
		float spanY = 0;
		if (curL < perL)
		{
			spanX = -(viewBox.getBoxWidth() - (curL - perL))
					/ (float) viewBox.getBoxWidth();
		}
		else
		{
			spanX = (viewBox.getBoxWidth() - (curL - perL))
					/ (float) viewBox.getBoxWidth();
		}
		if (curT < perT)
		{
			spanY = -(viewBox.getBoxHight() - (curT - perT))
					/ (float) viewBox.getBoxHight();
		}
		else
		{
			spanY = (viewBox.getBoxHight() - (curT - perT))
					/ (float) viewBox.getBoxHight();
		}
		if (Math.abs(spanX) < Math.abs(spanY))
		{
			if (spanX < 0)
				return which + 1;
			return which - 1;
		}
		else
		{
			if (spanY < 0)
				return which + 3;
			return which - 3;
		}
	}

	public void startDrag(View v)
	{
		int which = viewBox.whichBox(v);
		if (Main.boxList[which])
		{
			findOverView(which).reLayout(
					findEmptyPlace(v.getLeft(), v.getTop(), eV.getLeft(),
							eV.getTop(), which));

			// System.out.println();
		}
	}
}
