package com.smallteam.xyt.util;

import java.lang.ref.WeakReference;

public final class Log {

	public static final boolean DEBUG = true;
	public static final boolean INFO = false;
	public static final boolean WARN = true;
	public static final boolean ERROR = true;

	public static int d(String tag, String msg) {
		if (DEBUG) {
			return android.util.Log.d(tag, msg);
		} else {
			return 0;
		}
	}
	
	public static int d(Object obj, String msg) {
		if (DEBUG) {
			return android.util.Log.d(getWeakRef(obj).getClass().getSimpleName(), msg);
		} else {
			return 0;
		}
	}

	public static int i(String tag, String msg) {
		if (INFO) {
			return android.util.Log.i(tag, msg);
		} else {
			return 0;
		}
	}

	public static int w(String tag, String msg) {
		if (WARN) {
			return android.util.Log.w(tag, msg);
		} else {
			return 0;
		}
	}

	public static int e(String tag, String msg) {
		if (ERROR) {
			return android.util.Log.e(tag, msg);
		} else {
			return 0;
		}
	}

	public static int e(String tag, String msg, Throwable e) {
		if (ERROR) {
			return android.util.Log.e(tag, msg, e);
		} else {
			return 0;
		}
	}
	
	private final static Object getWeakRef(Object obj)
	{ 
		WeakReference<Object> wr = new WeakReference<Object>(obj);
		return wr.get();
	}
}
