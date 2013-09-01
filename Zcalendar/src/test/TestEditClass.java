package test;

import com.example.zcalendar.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;

public class TestEditClass extends Activity {
	
	private ScrollView mScrollView;
	private MyTable MyTable;
	
	private static final String tag = "TestEditClass";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.editclass_layout);
		
		MyTable = (MyTable) findViewById(R.id.myTable1);
		mScrollView = (ScrollView) findViewById(R.id.scrollView1);
		
		mScrollView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_MOVE){
					//可以监听到ScrollView的滚动事件
					Log.i(tag,"ACTION_MOVE X="+mScrollView.getScrollX());
					if(MyTable.mIsOnClick)
						mHandler.sendEmptyMessage(1);
				}
				return false;
			}
		});
	}
	
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			MyTable.redraw();
		}
	};
}
