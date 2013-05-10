package com.file.service;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FileMainActivity extends Activity
{
	private Button save;
	private FileService fileService;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fileService = new FileService(getApplication());

		save = (Button) findViewById(R.id.save);
		save.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText filename = (EditText) findViewById(R.id.name);
				EditText contain = (EditText) findViewById(R.id.contain);
				String name = filename.getText().toString();
				String textString = contain.getText().toString();
				try
				{
					fileService.save(name, textString);
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}