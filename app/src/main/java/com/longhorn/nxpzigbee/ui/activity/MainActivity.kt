package com.longhorn.nxpzigbee.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.longhorn.nxpzigbee.R
import com.longhorn.nxpzigbee.base.BaseActivity
import com.longhorn.nxpzigbee.ui.fragment.GroupFragment
import com.longhorn.nxpzigbee.ui.fragment.HomeFragment
import com.longhorn.nxpzigbee.ui.fragment.MeFragment
import com.longhorn.nxpzigbee.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var mContent: Fragment? = null
    private var homeFragment: Fragment? = null
    private var groupFragment: Fragment? = null
    private var meFragment: Fragment? = null


    override fun initToolBar() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimaryDark), 0)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        homeFragment = HomeFragment()
        mContent = homeFragment
        supportFragmentManager.beginTransaction().add(R.id.content, mContent).commit()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun getLayoutId() = R.layout.activity_main


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchContent(mContent!!, homeFragment!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (groupFragment == null) {
                    groupFragment = GroupFragment()
                }
                switchContent(mContent!!, groupFragment!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                if (meFragment == null) {
                    meFragment = MeFragment()
                }
                switchContent(mContent!!, meFragment!!)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
    }

    private fun switchContent(from: Fragment, to: Fragment) {
        if (mContent !== to) {
            mContent = to
            val transaction = supportFragmentManager.beginTransaction()
            if (!to.isAdded) {
                transaction.hide(from).add(R.id.content, to).commit()
            } else {
                transaction.hide(from).show(to).commit()
            }
        }
    }
}
