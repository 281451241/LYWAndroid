package test;


import com.example.zcalendar.R;
import com.example.zcalendar.R.layout;
import com.smallteam.xyt.util.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onClickMorningFirCls(View view) {
		Log.d(this, "onClickMorningFirCls");
		
		Intent intent = new Intent(this, TestEditClass.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("class_index", "onClickMorningFirCls");
		startActivity(intent);
	}
	
	public void onClickMorningSecCls(View view) {
		Log.d(this, "onClickMorningSecCls");
		
		Intent intent = new Intent(this, TestEditClass.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("class_index", "onClickMorningFirCls");
		startActivity(intent);
	}
	
	public void onClickAfternoonFirCls(View view) {
		Log.d(this, "onClickAfternoonFirCls");
		
		Intent intent = new Intent(this, TestEditClass.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("class_index", "onClickMorningFirCls");
		startActivity(intent);
	}
	
	public void onClickAfternoonSecCls(View view) {
		Log.d(this, "onClickAfternoonSecCls");
		
		Intent intent = new Intent(this, TestEditClass.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("class_index", "onClickMorningFirCls");
		startActivity(intent);
	}
	
	public void onClickNightFirCls(View view) {
		Log.d(this, "onClickNightFirCls");
		
		Intent intent = new Intent(this, TestEditClass.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("class_index", "onClickMorningFirCls");
		startActivity(intent);
	}
	
	public void onClickNightSecCls(View view) {
		Log.d(this, "onClickNightSecCls");
		
		Intent intent = new Intent(this, TestEditClass.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("class_index", "onClickMorningFirCls");
		startActivity(intent);
	}
}
