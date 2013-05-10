package com.listapp.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.lyw.net.NetUtil;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.util.Log;

public class TestListApps extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mPkgm = this.getPackageManager();
		System.out.println(NetUtil.isNetWorkConnection(this));
		// listAppNames();
//		listPackages();
//		test();
		// new ListApps(this).listPackages();
	}

	private void listAppNames()
	{
		List<ApplicationInfo> mListAppcations = new LinkedList<ApplicationInfo>();

		mListAppcations = mPkgm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		Collections.sort(mListAppcations,
				new ApplicationInfo.DisplayNameComparator(mPkgm));// 排序

		for (ApplicationInfo appInfo : mListAppcations)
		{
			System.out.println(appInfo.loadLabel(mPkgm));
		}
	}

	private void listPackages()
	{
		List<PackageInfo> mListPackages = new LinkedList<PackageInfo>();

		// mListPackages = mPkgm.getPreferredPackages(0);
		mListPackages = mPkgm
				.getInstalledPackages(PackageManager.GET_ACTIVITIES);
		Log.d(this.toString(), mListPackages.size() + "");
		for (PackageInfo pi : mListPackages)
		{
			System.out.println("============================================");
			System.out.println(pi.applicationInfo.loadLabel(mPkgm));
			if (pi.versionName != null)
			{
				System.out.println(pi.versionName);
			}
			System.out.println(pi.packageName);
		}
	}
	
	private void test() {
		List<PackageInfo> list = mPkgm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		  StringBuilder stringBuilder = new StringBuilder();

		for (PackageInfo packageInfo : list) {

		   stringBuilder.append("package name:"+ packageInfo.packageName+ "\n");
		   ApplicationInfo applicationInfo = packageInfo.applicationInfo;
		   stringBuilder.append("应用名称:"+ applicationInfo.loadLabel(mPkgm)+ "\n");
		  
		if (packageInfo.permissions != null) 
		{
		    for (PermissionInfo p : packageInfo.permissions) {
		    stringBuilder.append("权限包括:"+ p.name+ "\n");
		    }
		  }
		   stringBuilder.append("\n");
		}
		
		System.out.println(stringBuilder.toString());
	}

	PackageManager mPkgm;

}
