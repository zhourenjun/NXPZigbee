package com.longhorn.nxpzigbee.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.longhorn.nxpzigbee.util.EventBusUtil


/**
 * Created by lighting0675 on 2017/6/10.
 */
abstract class BaseFragment : Fragment() {
    var mContext: Context? = null
    var isVisible: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isRegisterEventBus()) {
            EventBusUtil.register(this)
        }
    }

    open fun isRegisterEventBus(): Boolean {
        return false
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutResId(), container, false)
    }

    abstract fun getLayoutResId(): Int

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishCreateView(savedInstanceState)
    }

    abstract fun finishCreateView(savedInstanceState: Bundle?)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isVisible = true
            lazyLoad()
        } else {
            isVisible = false
            onInvisible()
        }
    }

    open fun lazyLoad() {

    }

    open fun onInvisible() {

    }

    override fun onDetach() {
        super.onDetach()
        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)

        } catch (e: NoSuchFieldException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this)
        }
    }
}