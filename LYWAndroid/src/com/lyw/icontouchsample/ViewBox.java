package com.lyw.icontouchsample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

public class ViewBox
{
	private int boxWidth;
	private int boxHight;

	public int whichBox(View v)
	{
		int x = (v.getLeft() + getBoxWidth() / 2) / getBoxWidth();
		int y = (v.getTop() + getBoxHight() / 2) / getBoxHight();
//		System.out.println(x + ": " + y);
		return 3 * y + x;
	}

	public int getLeft(int which)
	{
		return (which % 3) * getBoxWidth();
	}

	public int getTop(int which)
	{
		return (which / 3) * getBoxHight();
	}

	public int getBoxWidth()
	{
		return Main.SCREEN_WIDTH / 3;
	}

	public int getBoxHight()
	{
		return Main.SCREEN_HEIGHT / 5;
	}

}
