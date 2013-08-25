package com.example.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.preference.R;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private ListView lv;
	private BaseAdapter adapter;
	private List<Item> list;
	private ArrayList<String> data = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listview);

		lv = (ListView) findViewById(R.id.lv);

		for (int i = 0; i < 20; i++)
			data.add("aa" + i + "------" + i);

		// 若同时设setOnItemClickListener和getview中设置的onclicklistener，
		// onclicklistener会拦截setOnItemClickListener
		// 这里点击时，button会被覆盖；在getview中设置的onclick却不会覆盖；
		// lv.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View view, int position,
		// long arg3) {
		// }
		// });

		init();
		initAdapter();
	}

	// 数据初始化
	private void init() {
		if (list == null)
			list = new ArrayList<Item>();
		else
			list.clear();
		for (String s : data) {
			list.add(new Item(s, false));
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
			ViewHolder holder;
			if (convertView == null
					|| (holder = (ViewHolder) convertView.getTag()) == null) {
				convertView = View.inflate(MainActivity.this,
						R.layout.listview_item, null);

				holder = new ViewHolder();
				holder.tv = (TextView) convertView
						.findViewById(R.id.lv_item_tv);

				holder.bt = (Button) convertView.findViewById(R.id.lv_item_bt);

				holder.cb = (CheckBox) convertView
						.findViewById(R.id.lv_item_cb);
				convertView.setTag(holder);
			}
			Item item = getItem(position);
			holder.tv.setText(item.name);
			holder.cb.setChecked(item.isCheck);

			final CheckBox box = holder.cb;

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d(TAG, ("itemview onclicked!! pos: " + position));
					Item onClickitem = list.get(position);
					box.setChecked(!box.isChecked());
					onClickitem.isCheck = box.isChecked();
				}
			});

			holder.bt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d(TAG, ("button onclicked!! pos: " + position) + '\n'
							+ "item name: " + list.get(position).name);
				}
			});

			return convertView;
		}

	}

	private class Item {
		String name;
		boolean isCheck = false;

		Item(String name, boolean isBuy) {
			this.name = name;
			this.isCheck = isBuy;
		}
	}

	private class ViewHolder {
		TextView tv;
		Button bt;
		CheckBox cb;
	}
}
