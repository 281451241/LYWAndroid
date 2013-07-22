package com.example.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.preference.R;

public class MainActivity extends Activity {
	private ListView lv;
	private BaseAdapter adapter;
	private List<Item> list;
	private ArrayList<String> data = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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

//		adapter = new MyAdapter();
//		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				Item item = list.get(pos);
				System.out.println(item.name);
			}
		});
		
		init();
		initAdapter();
	}

	// 数据初始化
	private void init()
	{
		if (list == null)
			list = new ArrayList<Item>();
		else
			list.clear();
		for (String s : data)
		{
			list.add(new Item(s, false, LANGUAGE_STATE_UNPROVIDED));
		}
	}
	
	// 刷新适配器
	public void initAdapter() {
		if (adapter == null) {
			adapter = new MyAdapter();
			lv.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
	}

	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Item getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				final ViewGroup parent) {
			
			// TODO 呢度，需要弄一个pos记录每个item的位置，
			// 不然滚动完，就会消失
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

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					cb.setChecked(!cb.isChecked());
					System.out.println("itemview onclicked!! pos: " + position);
				}
			});

			bt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					System.out.println("button onclicked!! pos: " + position);
				}
			});

			return view;
		}

	}
	
	private class Item
	{
		String name;
		boolean isBuy = false;
		int useStatus = LANGUAGE_STATE_UNPROVIDED;

		Item(String name, boolean isBuy, int state)
		{
			this.name = name;
			this.isBuy = isBuy;
			this.useStatus = state;
		}
	}

	private class ViewHolder {
		TextView text;
		Button button;
		CheckBox box;
	}
	
	/**
	 * 表示该语言包仍未下载
	 */
	private final static int LANGUAGE_STATE_UNPROVIDED = 0;
	/**
	 * 表示该语言包已经下载,但还没启用
	 */
	private final static int LANGUAGE_STATE_PROVIDED = 1;
	/**
	 * 表示该语言包已经下载,并已启用
	 */
	private final static int LANGUAGE_STATE_ENABLED = 2;
}
