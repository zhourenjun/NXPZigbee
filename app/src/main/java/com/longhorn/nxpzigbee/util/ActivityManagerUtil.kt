package com.longhorn.nxpzigbee.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context


/**
 * Created by lighting0675 on 2017/6/10.
 * 用于Activity管理和应用程序退出
 */
object ActivityManagerUtil {

    val list = mutableListOf<Activity>()

    fun addActivity(activity: Activity) {
        list.add(activity)
    }

    fun finishActivity(activity: Activity) {
            list.remove(activity)
            if (!activity.isFinishing) {
                activity.finish()
            }
    }

    fun finishAllActivity(){
        for (activity in list) {
            activity.finish()
        }
        list.clear()
    }

    fun AppExit(context: Context){
        finishAllActivity()
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}