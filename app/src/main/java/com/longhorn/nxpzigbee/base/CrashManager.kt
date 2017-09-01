package com.longhorn.nxpzigbee.base

import com.longhorn.nxpzigbee.util.LogUtil

/**
 * Created by lighting0675 on 2017/6/10.
 */
class CrashManager : Thread.UncaughtExceptionHandler{

    companion object {
        fun getInstance(): CrashManager = CrashManager()
    }
    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }
    override fun uncaughtException(t: Thread?, e: Throwable?) {
        LogUtil.e(e.toString())
    }
}