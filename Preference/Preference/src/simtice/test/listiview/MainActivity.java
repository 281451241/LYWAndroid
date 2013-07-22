package simtice.test.listiview;

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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.preference.R;

public class MainActivity extends Activity implements OnClickListener{
	TextView tv = null;
	ListView lv = null;
	String name[] = { 
			"G1", "G2", "G3", "G4", 
			"G5", "G6", "G7", "G8", "G9",
			"G10", "G11", "G12", "G13", "G14" };
	
	private List<Item> list;
	private List<String> data;
	private MyAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv = (TextView) this.findViewById(R.id.tv);
		lv = (ListView) this.findViewById(R.id.lv);
		
		this.findViewById(R.id.selectall).setOnClickListener(this);
		this.findViewById(R.id.inverseselect).setOnClickListener(this);
		this.findViewById(R.id.cancel).setOnClickListener(this);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Item item = list.get(arg2);
				item.b = !item.b;//ȡ��
				initAdapter();
			}
		});
		
		init();
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.selectall:
			int size1 = list.size();
			for(int i=0;i<size1;i++){
				list.get(i).b = true;
			}
			break;
		case R.id.inverseselect:
			int size2 = list.size();
			for(int i=0;i<size2;i++){
				Item item = list.get(i);
				item.b = !item.b;//ȡ��
			}
			break;
		case R.id.cancel:
			int size3 = list.size();
			for(int i=0;i<size3;i++){
				list.get(i).b = false;
			}
			break;
		}
		initAdapter();
	}
	
	//��ݳ�ʼ��
	private void init(){
		if(list == null)
			list = new ArrayList<Item>();
		else
			list.clear();
		if(data==null)
			data = new ArrayList<String>();
		for(String s:name){
			list.add(new Item(s,false));
		}
		initAdapter();
	}
	
	//ˢ��������
	public void initAdapter() {
		if(adapter == null){
			adapter = new MyAdapter();
			lv.setAdapter(adapter);
		}else{
			adapter.notifyDataSetChanged();
		}
		
		int size = list.size();
		data.clear();
		for(int i=0;i<size;i++){
			if(list.get(i).b)
				//count++;
				data.add(name[i]);
			else
				data.remove(name[i]);
		}
		//tv.setText("��ѡ�� "+count+" ��");
		tv.setText("��ѡ�� "+data.size()+" ��");
	}
	

	//Ϊlistview�Զ����������ڲ���
	class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Item getItem(int arg0) {
			return list.get(arg0);
		}
		
		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			ViewHolder holder;
			if(view == null || (holder=(ViewHolder)view.getTag()) == null){
				view = View.inflate(MainActivity.this, R.layout.listviewitem, null);
				holder = new ViewHolder();
				holder.tv = (TextView) view.findViewById(R.id.item_tv);
				holder.cb = (CheckBox) view.findViewById(R.id.item_cb);
				view.setTag(holder);
			}
			Item item = getItem(position);
			holder.tv.setText(item.name);
			holder.cb.setChecked(item.b);
			return view;
		}
	}
	
	class Item{
		public String name;
		public boolean b = false;
		public Item(String name,boolean b){
			this.name = name;
			this.b = b;
		}
	}
	
	class Tag{
		public TextView tv = null;
		public CheckBox cb = null;
	}

	class ViewHolder {
		public TextView tv = null;
		public CheckBox cb = null;
	}
}