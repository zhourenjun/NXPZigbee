package com.longhorn.nxpzigbee.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.view.MenuItem
import com.longhorn.nxpzigbee.R
import com.longhorn.nxpzigbee.base.BaseFragment
import com.longhorn.nxpzigbee.ui.adapter.DevicePageAdapter
import kotlinx.android.synthetic.main.frag_home.*

/**
 * Created by 仁军 on 2017/7/28.
 */
class HomeFragment : BaseFragment() {

    override fun getLayoutResId() = R.layout.frag_home

    override fun finishCreateView(savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.title_device)
        toolbar.inflateMenu(R.menu.toolbar_device)
        toolbar.setOnMenuItemClickListener({ item -> onMenuItemClick(item) })
        toolbar.setTitleTextColor(resources.getColor(R.color.white))

        vp.adapter = DevicePageAdapter(activity, childFragmentManager)
        vp.offscreenPageLimit = 2
        tab_layout.setupWithViewPager(vp)
        tab_layout.tabMode = TabLayout.MODE_FIXED
//        swipeRl.isRefreshing = true
        swipeRl.setColorSchemeColors(Color.BLUE, Color.RED, Color.BLACK)
        swipeRl.setOnRefreshListener {
            Handler().postDelayed({swipeRl.isRefreshing = false},2000)
        }
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        val menuItemId = item.itemId
        when(menuItemId){
            R.id.action_renew -> {}
            R.id.action_open -> {}
            R.id.action_close -> {}
            R.id.action_add -> {}
            R.id.action_gateway -> {}
        }
        return true
    }
}