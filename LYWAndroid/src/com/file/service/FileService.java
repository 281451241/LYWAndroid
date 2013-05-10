package com.file.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;

public class FileService
{
	private Context context;

	public FileService(Context context)
	{
		super();
		this.context = context;
	}

	/**
	 * 
	 * @param filename
	 * @param contain
	 * @throws Exception
	 */
	public void save(String filename, String contain) throws Exception
	{
		FileOutputStream outputStream = context.openFileOutput(filename,
				Context.MODE_PRIVATE);
		outputStream.write(contain.getBytes());
		outputStream.close();
	}

	/**
	 * 存储的SD卡中
	 * 
	 * @param filename
	 * @param content
	 * @throws Exception
	 */
	public void saveToSDcard(String filename, String content) throws Exception
	{
		File file = new File(Environment.getExternalStorageDirectory(),
				filename);
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(content.getBytes());
		outputStream.close();
	}

	/**
	 * 从手机内存（ROM）中读取数据
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public String readFile(String filename) throws Exception
	{
		FileInputStream inputStream = context.openFileInput(filename);
		byte[] buffer = new byte[1024];
		int len = 0;
		// 写入到手机内存中
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1)
		{
			outputStream.write(buffer, 0, len);
		}
		byte[] data = outputStream.toByteArray();// 得到二进制数据
		inputStream.close();
		outputStream.close();

		return new String(data);
	}
}