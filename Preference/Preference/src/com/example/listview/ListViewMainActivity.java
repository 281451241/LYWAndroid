package com.example.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.preference.R;

public class ListViewMainActivity extends Activity
{
	ListView lv;

	ArrayList<String> data = new ArrayList<String>();

	private List<Item> list;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listview);

		lv = (ListView) findViewById(R.id.lv);
		for(int i=0; i<20; i++) 
			data.add("aa"+i+"------"+i);
		lv.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3)
			{
				Item item = list.get(pos);
				if(view instanceof LinearLayout){
					System.out.println("hahahaaaaaaaaaaa");
					switch (item.useStatus)
					{
					case LANGUAGE_STATE_UNPROVIDED:
						item.useStatus = LANGUAGE_STATE_PROVIDED;
						break;
					case LANGUAGE_STATE_PROVIDED:
						item.useStatus = LANGUAGE_STATE_ENABLED;
						break;
					case LANGUAGE_STATE_ENABLED:
						item.useStatus = LANGUAGE_STATE_PROVIDED;
						break;
	
					default:
						break;
					}
				} else {
					System.out.println("item: " + item.name);
					item.isBuy = !item.isBuy;// 取反
				}
				initAdapter();
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
	public void initAdapter()
	{
		if (adapter == null)
		{
			adapter = new MyAdapter();
			lv.setAdapter(adapter);
		}
		else
		{
			adapter.notifyDataSetChanged();
		}
	}

	// 为listview自定义适配器内部类
	class MyAdapter extends BaseAdapter
	{
		@Override
		public int getCount()
		{
			return list.size();
		}

		@Override
		public Item getItem(int arg0)
		{
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0)
		{
			return arg0;
		}

		@Override
		public View getView(final int position, View view, ViewGroup arg2)
		{
			ViewHolder holder;
			if (view == null || (holder = (ViewHolder) view.getTag()) == null)
			{
				view = View.inflate(ListViewMainActivity.this,
						R.layout.listview_item, null);
				view.setBackgroundResource(android.R.drawable.list_selector_background);
				holder = new ViewHolder();
				holder.tv = (TextView) view.findViewById(R.id.lv_item_tv);
				
				holder.bt = (Button) view.findViewById(R.id.lv_item_bt);
				holder.bt.setBackgroundResource(android.R.drawable.btn_default);
				holder.bt.setFocusable(false);
				
				holder.cb = (CheckBox) view.findViewById(R.id.lv_item_cb);
				view.setTag(holder);
			}
			Item item = getItem(position);
			holder.tv.setText(item.name);
			switch (item.useStatus)
			{
			case LANGUAGE_STATE_UNPROVIDED:
				holder.bt.setVisibility(View.INVISIBLE);
				holder.cb.setVisibility(View.INVISIBLE);
				break;
			case LANGUAGE_STATE_PROVIDED:
				holder.bt.setVisibility(item.isBuy ? View.INVISIBLE
						: View.VISIBLE);
//				view.setBackgroundResource(android.R.drawable.list_selector_background);
//				holder.bt.setBackgroundResource(android.R.drawable.btn_default);
				holder.cb.setVisibility(View.VISIBLE);
				holder.cb.setChecked(false);
				break;
			case LANGUAGE_STATE_ENABLED:
				holder.bt.setVisibility(item.isBuy ? View.INVISIBLE
						: View.VISIBLE);
//				view.setBackgroundResource(android.R.drawable.list_selector_background);
//				holder.bt.setBackgroundResource(android.R.drawable.btn_default);
				holder.cb.setVisibility(View.VISIBLE);
				holder.cb.setChecked(true);
				break;

			default:
				break;
			}
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

	private class ViewHolder
	{
		TextView tv = null;
		Button bt = null;
		CheckBox cb = null;
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
