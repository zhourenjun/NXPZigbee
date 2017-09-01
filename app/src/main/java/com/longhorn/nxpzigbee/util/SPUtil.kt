package com.longhorn.nxpzigbee.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by lighting0675 on 2017/6/10.
 */
object SPUtil {

    var sp: SharedPreferences? = null
    fun getInstance(context: Context): SharedPreferences? {
        sp = context.getSharedPreferences("sp_name_ble",Context.MODE_PRIVATE)
        return sp
    }

    fun clearAllData(){
        val editor = sp!!.edit()
        editor.clear()
        editor.commit()
    }

    fun setStringValue(key: String, value: String) {
        val editor = sp!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getStringValue(key: String, defValue: String): String {
        return sp!!.getString(key, defValue)
    }

    fun setBooleanValue(key: String, value: Boolean) {
        val editor = sp!!.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBooleanValue(key: String, defValue: Boolean): Boolean {
        return sp!!.getBoolean(key, defValue)
    }

    fun setIntValue(key: String, value: Int) {
        val editor = sp!!.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getIntValue(key: String, defValue: Int): Int {
        return sp!!.getInt(key, defValue)
    }

}