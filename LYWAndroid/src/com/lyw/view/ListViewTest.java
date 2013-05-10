package com.lyw.view;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewTest extends ListView{
	
	TextView tv;

	public ListViewTest(Context context) {
		super(context);
		
		for(int i=0; i<8; i++) {
			tv = new TextView(context);
			tv.setText("aaaaaa"+i);
			addView(tv);
		}
	}

}
