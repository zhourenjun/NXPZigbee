package com.longhorn.nxpzigbee.util

import com.longhorn.nxpzigbee.model.Data
import org.greenrobot.eventbus.EventBus

/**
 * Created by lighting0675 on 2017/6/10.
 */
object EventBusUtil {

    fun register(subscriber:Any){
        EventBus.getDefault().register(subscriber)
    }

    fun unregister(subscriber: Any){
        EventBus.getDefault().unregister(subscriber)
    }

    fun sendEvent(data: Data<Any>){
        EventBus.getDefault().post(data)
    }
}