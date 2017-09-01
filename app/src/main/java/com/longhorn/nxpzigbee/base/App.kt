package com.longhorn.nxpzigbee.base

import android.app.Application
import android.os.Build
import android.os.StrictMode
import com.longhorn.nxpzigbee.util.SPUtil
import kotlin.properties.Delegates


/**
 * Created by lighting0675 on 2017/6/10.
 */
class App : Application() {

    companion object{
        var instance: App by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
//        CrashManager.getInstance()
    }


    fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }
        SPUtil.getInstance(applicationContext)
    }

}