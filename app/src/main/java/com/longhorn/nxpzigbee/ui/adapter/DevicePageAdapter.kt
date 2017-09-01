package com.longhorn.nxpzigbee.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.longhorn.nxpzigbee.R
import com.longhorn.nxpzigbee.ui.fragment.LightFragment
import com.longhorn.nxpzigbee.ui.fragment.PlugFragment
import com.longhorn.nxpzigbee.ui.fragment.SensorFragment


/**
 * Created by 仁军 on 2017/7/28.
 */
class DevicePageAdapter(val mContext: Context,fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return LightFragment()
            1 -> return PlugFragment()
            2 -> return SensorFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return mContext.getString(R.string.title_light)
            1 -> return mContext.getString(R.string.title_plug)
            2 -> return mContext.getString(R.string.title_sensor)
            else -> return ""
        }
    }
}