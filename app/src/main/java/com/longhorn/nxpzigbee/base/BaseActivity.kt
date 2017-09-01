package com.longhorn.nxpzigbee.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.longhorn.nxpzigbee.R
import com.longhorn.nxpzigbee.util.ActivityManagerUtil
import com.longhorn.nxpzigbee.util.EventBusUtil

/**
 * Created by lighting0675 on 2017/6/10.
 */

abstract class BaseActivity : AppCompatActivity() {
     var mContext : Context?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutId())
        mContext = this
        initViews(savedInstanceState)
        initToolBar()
        ActivityManagerUtil.addActivity(this)
        if (isRegisterEventBus()) {
            EventBusUtil.register(this)
        }
    }

    open fun isRegisterEventBus(): Boolean {
        return false
    }

    abstract fun initToolBar()

    abstract fun  initViews(savedInstanceState: Bundle?)

    abstract fun  getLayoutId(): Int

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_bottom_in, R.anim.fade_out)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.slide_bottom_in, R.anim.fade_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slide_bottom_out)
        ActivityManagerUtil.finishActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManagerUtil.finishActivity(this)
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this)
        }
    }
}