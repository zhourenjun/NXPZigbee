package com.longhorn.telinkmesh.base

import android.content.Context
import android.support.annotation.StringRes
import android.view.Gravity
import android.view.View
import android.widget.Toast

/**
 * Created by lighting0675 on 2017/6/14.
 */
val View.ctx: Context
    get() = context

fun Context.showToast(message:String) :Toast{
    var toast = Toast.makeText(this,message,Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
    return toast
}

fun Context.showToast(@StringRes resId:Int) :Toast{
    var toast = Toast.makeText(this,resId,Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
    return toast
}