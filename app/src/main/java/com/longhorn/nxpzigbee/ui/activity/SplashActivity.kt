package com.longhorn.nxpzigbee.ui.activity

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import com.longhorn.nxpzigbee.R
import com.longhorn.nxpzigbee.base.BaseActivity
import com.longhorn.nxpzigbee.util.Constant
import com.longhorn.nxpzigbee.util.MPermissionUtils
import com.longhorn.nxpzigbee.util.SPUtil
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * Created by lighting0675 on 2017/6/10.
 */
class SplashActivity : BaseActivity() {
   private lateinit var animatorSet: AnimatorSet

    override fun initToolBar() {
    }

    override fun initViews(savedInstanceState: Bundle?) {
        beginAlpha()
    }

    override fun getLayoutId(): Int {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash
    }

    private fun beginAlpha() {
        val alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.13f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.13f)
        val objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(img, alpha, scaleX, scaleY)
        val objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(tv1, alpha, scaleX, scaleY)
        val objectAnimator3 = ObjectAnimator.ofPropertyValuesHolder(tv2, alpha, scaleX, scaleY)
        animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator1,objectAnimator2,objectAnimator3)
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.duration = 1500
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                val permissions = arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE)
                MPermissionUtils.requestPermissionsResult(this@SplashActivity, 1, permissions, object : MPermissionUtils.OnPermissionListener {
                    override fun onPermissionGranted() {
                        redirectTo()
                    }

                    override fun onPermissionDenied() {
                        MPermissionUtils.showTipsDialog(this@SplashActivity)
                    }
                })
            }

            override fun onAnimationCancel(animator: Animator) {}

            override fun onAnimationRepeat(animator: Animator) {}
        })
        animatorSet.start()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions as Array<String>, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun redirectTo(){
        if (SPUtil.getBooleanValue(Constant.SP_IS_FIRST_USE, true)) {
            startActivity(Intent(mContext, AddGatewayActivity::class.java))
        } else {
            startActivity(Intent(mContext, MainActivity::class.java))
        }
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        animatorSet.cancel()
    }
}






