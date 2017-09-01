package com.longhorn.nxpzigbee.ui.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import com.longhorn.nxpzigbee.R
import com.longhorn.nxpzigbee.base.BaseActivity
import com.longhorn.nxpzigbee.util.SPUtil
import kotlinx.android.synthetic.main.activity_add_gateway.*

/**
 * Created by 仁军 on 2017/7/31.
 */
class AddGatewayActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_add_gateway

    override fun initToolBar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        tv_title.text = getString(R.string.add_gateway)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
    }

    override fun initViews(savedInstanceState: Bundle?) {

        bt_go.setOnClickListener {
            val apSsid = wifi.text.toString()
            val apPassword = password.text.toString()
            SPUtil.setStringValue(apSsid, apPassword)
            val apBssid = getWifiConnectedBssid()
            //todo
        }

        rl_skip.setOnClickListener {
            startActivity(Intent(mContext, MainActivity::class.java))
            finish()
        }
    }


    override fun onResume() {
        super.onResume()
        var apSsid = getWifiConnectedSsid()
        if (apSsid.isNotEmpty()) {
            rl_hint.visibility = View.GONE
            wifi.setText(apSsid)
            val pwd = SPUtil.getStringValue(apSsid, "")
            password.setText(pwd)
            wifi.isEnabled = false
            bt_go.isEnabled = true
        } else {
            rl_hint.visibility = View.VISIBLE
            bt_go.isEnabled = false
        }
    }


    private fun getWifiConnectedSsid(): String {
        val mWifiInfo = getConnectionInfo()
        var ssid = ""
        if (mWifiInfo != null && isWifiConnected()) {
            val len = mWifiInfo.ssid.length
            ssid = if (mWifiInfo.ssid.startsWith("\"") && mWifiInfo.ssid.endsWith("\"")) {
                mWifiInfo.ssid.substring(1, len - 1)
            } else {
                mWifiInfo.ssid
            }
        }
        return ssid
    }

    private fun getConnectionInfo(): WifiInfo {
        val mWifiManager = application.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return mWifiManager.connectionInfo
    }

    private fun isWifiConnected(): Boolean {
        val mWiFiNetworkInfo = getWifiNetworkInfo()
        var isWifiConnected = false
        if (mWiFiNetworkInfo != null) {
            isWifiConnected = mWiFiNetworkInfo.isConnected
        }
        return isWifiConnected
    }

    private fun getWifiNetworkInfo(): NetworkInfo? {
        val mConnectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    }

    private fun getWifiConnectedBssid(): String {
        val mWifiInfo = getConnectionInfo()
        var bssid = ""
        if (mWifiInfo != null && isWifiConnected()) {
            bssid = mWifiInfo.bssid
        }
        return bssid
    }
}