package com.example.listview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.preference.R;

public class ListViewMainActivity extends Activity
{
	ListView lv;

	ArrayList<String> data = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listview);

		lv = (ListView) findViewById(R.id.lv);

		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		data.add("aaaaaaaaaaaaaaaaaaaa");
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv.setAdapter(new MyAdapter());
	}

	class ViewHolder
	{
		TextView text;
		Button button;
		CheckBox box;
	}

	private class MyAdapter extends BaseAdapter
	{
		ViewHolder holder;

		@Override
		public int getCount()
		{
			return data.size();
		}

		@Override
		public Object getItem(int position)
		{
			return getView(position, null, null);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				final ViewGroup parent)
		{
System.out.println("getView");
			if (convertView == null)
			{
				convertView = LayoutInflater.from(ListViewMainActivity.this).inflate(
						R.layout.listview_item, null);
				convertView
						.setBackgroundResource(android.R.drawable.list_selector_background);
				holder = new ViewHolder();
				holder.text = (TextView) convertView
						.findViewById(R.id.lv_item_tv);
				holder.button = (Button) convertView
						.findViewById(R.id.lv_item_bt);
				holder.box = (CheckBox) convertView
						.findViewById(R.id.lv_item_cb);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			holder.text.setText(data.get(position));
			holder.button.setBackgroundResource(android.R.drawable.btn_default);
			holder.button.setFocusable(false);

			holder.button.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					holder.box.setChecked(!holder.box.isChecked());
					System.out.println("button onclicked!! pos: " + position);
				}
			});

			return convertView;
		}

	}
}
