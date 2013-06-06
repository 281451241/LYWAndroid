package com.lyw.io.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.file.service.R;
import com.lyw.util.ByteUtil;

public class MainActivity extends Activity
{
	private EditText ed_tex;
	private Button btn_sent;
	private int SERVER_POINT = 5778;
	private String SERVER_ADDRESS = "192.168.1.100";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_socket);
		init();
	}

	private void init()
	{
		// TODO Auto-generated method stub
		ed_tex = (EditText) findViewById(R.id.ed_texts);
		btn_sent = (Button) findViewById(R.id.btn_sents);
		btn_sent.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String str = ed_tex.getText().toString().trim();
//				if (v != null && !str.equals(""))
//				{
					System.out.println("准备发送....");
					new SendThred().start();
//				}
			}
		});
	}

	class SendThred extends Thread implements Runnable
	{
		String Str_message;
		User u;
		JSONObject json;

		public SendThred(String str)
		{
			this.Str_message = str;
		}
		
		public SendThred()
		{
			u = new User();
			u.setId(1);
			u.setName("smim");
			
			json = new JSONObject();
			try
			{
				json.put("name", "sm");
			}
			catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void run()
		{
			try
			{
				System.out.println("正在连接服务器...");
				Socket s = new Socket(SERVER_ADDRESS, SERVER_POINT);
				System.out.println("成功连接服务器...");
				System.out.println("准备发送数据...");
//				json.toString().getBytes()
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				out.write(ByteUtil.getBytes(json));
//				out.write(json.toString());
//				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
//				out.writeObject(json);
//				out.flush();
				System.out.println("成功发送数据...");
				System.out.println("准备接收服务器返回结果...");
//				DataInputStream is = new DataInputStream(s.getInputStream());
//				System.out.println("成功接收服务器返回数据：" + is.readUTF());
				// Toast.makeText(getApplicationContext(), "成功接收服务器返回数据：" +
				// is.readUTF(), Toast.LENGTH_SHORT).show();
//				is.close();
				out.close();
				s.close();
			}
			catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}