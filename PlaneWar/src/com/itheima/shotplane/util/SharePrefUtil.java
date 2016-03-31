package com.itheima.shotplane.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreferences操作封装
 * 
 * @author lxj
 * @version 1.0
 */
public class SharePrefUtil {
	private static String tag = SharePrefUtil.class.getSimpleName();
	private final static String SP_NAME = "config";//程序缓存文件
	private static SharedPreferences sp;

	public interface KEY {
		String SCORE = "score"; // 

	}

	/**
	 * 保存布尔值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(Context context, String key, boolean value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 保存字符串
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveString(Context context, String key, String value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putString(key, value).commit();
	}

	/**
	 * 保存long型
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveLong(Context context, String key, long value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putLong(key, value).commit();
	}

	/**
	 * 保存int型
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveInt(Context context, String key, int value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * 保存float型
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveFloat(Context context, String key, float value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putFloat(key, value).commit();
	}

	/**
	 * 获取字符值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getString(key, defValue);
	}

	/**
	 * 获取int值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getInt(key, defValue);
	}

	/**
	 * 获取long值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getLong(key, defValue);
	}

	/**
	 * 获取float值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Context context, String key, float defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getFloat(key, defValue);
	}

	/**
	 * 获取布尔值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getBoolean(key, defValue);
	}


}
