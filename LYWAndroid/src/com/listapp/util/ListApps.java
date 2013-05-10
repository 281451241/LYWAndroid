package com.listapp.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

public class ListApps
{
	Context mContext;
	public ListApps(Context context)
	{
		mContext = context;
	}
	public void listPackages()
	{
		ArrayList<PInfo> apps = getInstalledApps(false); /*
														 * false = no system
														 * packages
														 */
		final int max = apps.size();
		for (int i = 0; i < max; i++)
		{
			apps.get(i).prettyPrint();
		}
	}

	private ArrayList<PInfo> getInstalledApps(boolean getSysPackages)
	{
		ArrayList<PInfo> res = new ArrayList<PInfo>();
		List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
		for (int i = 0; i < packs.size(); i++)
		{
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && (p.versionName == null))
			{
				continue;
			}
			PInfo newInfo = new PInfo();
			newInfo.appname = p.applicationInfo.loadLabel(mContext.getPackageManager())
					.toString();
			newInfo.pname = p.packageName;
			newInfo.versionName = p.versionName;
			newInfo.versionCode = p.versionCode;
			newInfo.icon = p.applicationInfo.loadIcon(mContext.getPackageManager());
			res.add(newInfo);
		}
		return res;
	}

	class PInfo
	{
		private String appname = "";
		private String pname = "";
		private String versionName = "";
		private int versionCode = 0;
		private Drawable icon;

		private void prettyPrint()
		{
			System.out.println(appname + "\t" + pname + "\t" + versionName + "\t"
					+ versionCode + "\t");
		}
	}
}
