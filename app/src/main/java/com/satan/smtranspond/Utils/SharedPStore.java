
package com.satan.smtranspond.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * 对SharedPreferences的封装
 */
public class SharedPStore {

    private Context mContext = null;

    /**
     * 默认文件名
     */
    private String fileName = "smt_config";

    /**
     * SharedPreferences对象
     */
    private SharedPreferences mSharedPreferences = null;

    /**
     * Editor
     */
    private SharedPreferences.Editor mEditor = null;

    /**
     * 实例化保存数据对象
     * 
     * @param context 上下文 {@link Context}
     * @param name 文件的名称 可以为null,默认uu_config
     */
    public SharedPStore(Context context, String name) {
        this.mContext = context;
        if (!TextUtils.isEmpty(name)) {
            fileName = name;
        }
        mSharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public SharedPStore(Context mContext) {
        this(mContext, "");
    }

    /**
     *  写入一个string数据
     * 
     * @param key 对象key
     * @param value 对象值
     * @return void
     */
    public void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     *  获取一个string数据
     * 
     * @param key 对象key
     * @param defaultValue 读取失败时的默认值
     * @return String
     */
    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    /**
     *  写入一个int数据
     * 
     * @param key 对象key
     * @param value 对象值
     * @return void
     */
    public void putInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     *  读取一个int数据
     * 
     * @param key 对象key
     * @param defaultValue 读取失败时的默认值
     * @return int
     */
    public int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    /**
     *  写入一个float数据
     * 
     * @param key 对象key
     * @param value 对象值
     * @return void
     */
    public void putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.commit();
    }

    /**
     * 读取一个float数据
     * 
     * @param key 对象key
     * @param defaultValue 读取失败时的默认值
     * @return float
     */
    public float getFloat(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * 写入一个long数据
     * 
     * @param key 对象key
     * @param value 对象值
     * @return void
     */
    public void putLong(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * 读取一个long数据
     * @param key 对象key
     * @param defaultValue 读取失败时的默认值
     * @return long
     */
    public long getLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    /**
     *  写入一个boolean 数据
     * 
     * @param key 对象key
     * @param value 对象值
     * @return void
     */
    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    /**
     *  读取一个boolean数据
     * 
     * @param key 对象key
     * @param defaultValue 读取失败时的默认值
     * @return boolean
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     *  commit
     * @return void
     */
    public void commit() {
        mEditor.commit();
    }

    /**
     * 是否含有某个值
     * 
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }

    /**
     * 删除某个值
     * 
     * @param key 键
     */
    public void remove(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }

    /**
     * 清空
     */
    public void clear() {
        mEditor.clear().commit();
    }

}
