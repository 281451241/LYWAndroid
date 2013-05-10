package com.mysession.test;
 
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;
 
public class SessionActivityTest extends InstrumentationTestCase {
 
    private Instrumentation mInstrumentation;
     private ActivityMonitor mSessionMonitor;
     private Activity mCurrentActivity, mSessionActivity;
 
    private String TextNotEqual = "text not equal.";
     private static final String PackageName = "com.mysession";
 
    @Override
     protected void setUp() throws Exception {
 
// 初始化
         super.setUp();
 
        if (mInstrumentation == null) {
             mInstrumentation = getInstrumentation();
         }
 
        mSessionActivity = null;
     }
 
    @Override
     protected void tearDown() throws Exception {
         super.tearDown();
       
 //释放资源
         closeActivity(mSessionActivity);
         mCurrentActivity = null;
     }
 
    private void closeActivity(Activity activity) {
         if(activity != null){
             activity.finish();
             activity = null;
         }
       
     }
 
    public void openSessionActivity() {
 
// 打开session activity
         try {
             setUp();
         } catch (Exception e) {
             e.printStackTrace();
         }
 
        mSessionMonitor = mInstrumentation.addMonitor(
                 D_sessionActivity.class.getName(), null, false);
 
        Intent intent = new Intent(Intent.ACTION_MAIN);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         intent.setClassName(PackageName, D_sessionActivity.class.getName());
         mInstrumentation.startActivitySync(intent);
 
        mSessionActivity = getInstrumentation().waitForMonitor(mSessionMonitor);
         assertNotNull(mSessionActivity);
         mCurrentActivity = mSessionActivity;
     }
 //判断text是否正确
     public void assertTextEqual(int resId, String strText) {
         TextView textView = (TextView) mCurrentActivity.findViewById(resId);
         assertNotNull(textView);
 
        assertEquals(TextNotEqual,
                 strText,
                 textView.getText().toString());
     };
   
 // 模拟按钮点击事件
     public void clickButton(int resId){
         Button button = (Button) mCurrentActivity.findViewById(resId);
         assertNotNull(button);
       
         TouchUtils.clickView(this, button);
     }
 }