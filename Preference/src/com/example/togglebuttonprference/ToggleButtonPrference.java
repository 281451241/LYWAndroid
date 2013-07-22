package com.example.togglebuttonprference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.preference.R;

/**
 * @author Administrator自定义ToggleButtonPrference组件
 * 
 */
public class ToggleButtonPrference extends Preference implements
		OnClickListener
{
	private ToggleButtonOnClickListener mToggleButtonOnClickListener;
	private CheckBox mToggleButton;
	private Button mButton;

	// private boolean isCheck = true;

	public ToggleButtonPrference(Context context)
	{
		super(context);
		this.setSelectable(false);
	}

	public ToggleButtonPrference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		this.setSelectable(false);
	}

	@Override
	protected void onBindView(View view)
	{
		super.onBindView(view);
		mToggleButton = (CheckBox) view.findViewById(R.id.togglebutton);
		mToggleButton.setOnClickListener(this);
		// mToggleButton.setChecked(isCheck);
	}

	@Override
	public void onClick(View v)
	{
		if (mToggleButtonOnClickListener != null)
		{
			mToggleButtonOnClickListener.onClick(mToggleButton.isChecked());
			// isCheck = mToggleButton.isChecked() ;
		}
	}

	public interface ToggleButtonOnClickListener
	{
		void onClick(boolean isCheck);
	}

	/**
	 * @param mToggleButtonOnClickListener
	 *            点击监听事件
	 */
	public void setToggleButtonOnClickListener(
			ToggleButtonOnClickListener mToggleButtonOnClickListener)
	{
		this.mToggleButtonOnClickListener = mToggleButtonOnClickListener;
	}

	// public boolean defaultValues() {
	// return isCheck;
	// }

	// @Override
	// protected void onSetInitialValue(boolean restorePersistedValue, Object
	// defaultValue) {
	// super.onSetInitialValue(restorePersistedValue, defaultValue);
	// Boolean temp = restorePersistedValue ? false : (Boolean) defaultValue;
	// if (temp) {
	// persistBoolean(temp);
	// }
	// isCheck = temp;
	// }
}