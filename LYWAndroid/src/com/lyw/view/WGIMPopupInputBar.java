package com.lyw.view;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.file.service.R;

public class WGIMPopupInputBar
{

	private Context mContext;
	private PopupWindow mPopupWindow;
	private TextView mTextView;
	private boolean mIsHeightWrapContent = false;
	private boolean mIsWidthWrapContent = false;
	LinearLayout mLayout;

	public WGIMPopupInputBar(Context context, int width, int height)
	{
		// TODO Auto-generated constructor stub
		mContext = context;
		mLayout = new LinearLayout(context);
		mLayout.setOrientation(LinearLayout.VERTICAL);
		mLayout.setPadding(4, 0, 4, 0);
		mTextView = new TextView(context);
		mTextView.setTextSize(50.0f);
		
		mLayout.addView(mTextView);

		if (width <= 0)
		{
			width = LayoutParams.WRAP_CONTENT;
			mIsWidthWrapContent = true;
		}
		if (height <= 0)
		{
			height = LayoutParams.WRAP_CONTENT;
			mIsHeightWrapContent = true;
		}

		mPopupWindow = new PopupWindow(mLayout, width, height);
		mPopupWindow.setBackgroundDrawable(context.getResources().getDrawable(
				com.file.service.R.drawable.majin_inputbar_bg_theme));
	}

	public void setOnTextViewClickListener(OnClickListener listener) {
		mTextView.setOnClickListener(listener);
	}
	
	public final void setText(String str)
	{
		mTextView.setText(str);
	}

	public void showAsDropDown(String items, View parent, int offX, int offY)
	{
		if (items == null)
			return;
		if (parent == null)
			return;

		Rect visibleRect = new Rect();
		{
			parent.getGlobalVisibleRect(visibleRect);
			int w = visibleRect.width();
			int h = visibleRect.height();

			int[] position = new int[2];
			parent.getLocationOnScreen(position);

			visibleRect.set(position[0], position[1], position[0] + w,
					position[1] + h);
		}

		int popupWindowHeight = (Dp2Px(mContext, 6) + getFontHeight(mContext
				.getResources().getDimension(R.dimen.wgim_popupmenu_textsize)))
				+ Dp2Px(mContext, 25);

		if (mIsHeightWrapContent)
		{
			mPopupWindow.setHeight(popupWindowHeight);
		}
		else
		{
			popupWindowHeight = mPopupWindow.getHeight();
		}


		int maxHeight = visibleRect.top - getBar(mContext);
		if (mPopupWindow.getHeight() > maxHeight)
		{
			mPopupWindow.setHeight(maxHeight);
			popupWindowHeight = maxHeight;
		}

		offY = -(visibleRect.height() + popupWindowHeight) + offY;

		if (offY > -popupWindowHeight)
		{
			offY = -popupWindowHeight;
		}

		mPopupWindow
				.showAtLocation(parent, Gravity.LEFT | Gravity.BOTTOM, 0, 0);
		mPopupWindow.update(parent, offX, offY, mPopupWindow.getWidth(),
				mPopupWindow.getHeight());
	}

	public final void updatePopupWindow(View parent, int offX, int offY,
			String str)
	{
		Rect visibleRect = getViewRect(parent);

		int popupWindowHeight = (Dp2Px(mContext, 6) + getFontHeight(mContext
				.getResources().getDimension(R.dimen.wgim_popupmenu_textsize)))
				+ Dp2Px(mContext, 25);

		if (mIsHeightWrapContent)
		{
			mPopupWindow.setHeight(popupWindowHeight);
		}
		else
		{
			popupWindowHeight = mPopupWindow.getHeight();
		}

		int maxHeight = visibleRect.top - getBar(mContext);
		if (mPopupWindow.getHeight() > maxHeight)
		{
			mPopupWindow.setHeight(maxHeight);
			popupWindowHeight = maxHeight;
		}

		offY = offY < 0 ? 0 : offY;
		offY = -(visibleRect.height() + popupWindowHeight) + offY;

		if (offY > -popupWindowHeight)
		{
			offY = -popupWindowHeight;
		}
		
		LayoutParams tvPara = (LayoutParams) mLayout.getLayoutParams();
		mLayout.requestLayout();
		mPopupWindow.setWidth(tvPara.width);
		mPopupWindow.update(parent, offX, offY, mPopupWindow.getWidth(),
				mPopupWindow.getHeight());
	}

	private static int getBar(Context context)
	{
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try
		{
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		return sbar;
	}

	private static int Dp2Px(Context context, float dp)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	private static int getFontHeight(float fontSize)
	{
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		FontMetrics fm = paint.getFontMetrics();
		return (int) Math.ceil(fm.descent - fm.ascent) + 2;
	}

	private final Rect getViewRect(View view)
	{
		Rect visibleRect = new Rect();
		{
			view.getGlobalVisibleRect(visibleRect);
			int w = visibleRect.width();
			int h = visibleRect.height();

			int[] position = new int[2];
			view.getLocationOnScreen(position);

			visibleRect.set(position[0], position[1], position[0] + w,
					position[1] + h);
		}
		return visibleRect;
	}

	public void dismiss()
	{
		mPopupWindow.dismiss();
	}

	public boolean isShowing()
	{
		return mPopupWindow.isShowing();
	}
}
