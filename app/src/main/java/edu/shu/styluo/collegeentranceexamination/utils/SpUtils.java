package edu.shu.styluo.collegeentranceexamination.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用于sharedpreferences存储的工具类
 * @author  author: styluo
 * @Time date: 2017/4/17 22:02
 * @Email e-mail: shu_jiahuili@foxmail.com
 */
public class SpUtils {
    public static final String FIRST_OPEN = "IS_FIRST";
	private static final String SP_FILE_NAME = "PREF_COLLEGE_ENTRANCE"; //SP缓存文件名

    /**
     * 保存第一次登录状态到sp
     * @param context Context 上下文
     * @param key 所需存储的对应key
     * @param value boolean 是否是第一次登录
     * @return
     */
    public static void saveIsFirst(Context context, String key, boolean value) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 获取是否第一次登陆状态
     * @param context Contexxt 上下文
     * @param key 所需要读取的key
     * @param valueDefault 如果没有对应key的默认值
     * @return boolean 是否是第一次登录bool值
     */
    public static boolean loadIsFirst(Context context, String key, boolean valueDefault) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, valueDefault);
        return value;
    }


}
