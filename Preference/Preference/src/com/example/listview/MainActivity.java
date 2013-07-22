package com.example.listview;

import java.util.ArrayList;

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

public class MainActivity extends Activity
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
			final View view;
			TextView tv;
			final Button bt;
			final CheckBox cb;

			view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.listview_item, null);
			view.setBackgroundResource(android.R.drawable.list_selector_background);

			tv = (TextView) view.findViewById(R.id.lv_item_tv);
			tv.setText(data.get(position));

			bt = (Button) view.findViewById(R.id.lv_item_bt);
			bt.setBackgroundResource(android.R.drawable.btn_default);
			bt.setFocusable(false);

			cb = (CheckBox) view.findViewById(R.id.lv_item_cb);

			view.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					cb.setChecked(!cb.isChecked());
					System.out.println("itemview onclicked!! pos: " + position);
				}
			});

			bt.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					System.out.println("button onclicked!! pos: " + position);
				}
			});

			return view;
		}

	}
}
