package com.longhorn.telinkmesh.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import com.longhorn.nxpzigbee.R


class RoundButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                            defStyle: Int = 0) : TextView(context, attrs, defStyle) {

    init {


        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundButton)

        val pressedRatio = a.getFloat(R.styleable.RoundButton_btnPressedRatio, 0.80f)
        val cornerRadius = a.getLayoutDimension(R.styleable.RoundButton_btnCornerRadius, 0)

        var solidColor = a.getColorStateList(R.styleable.RoundButton_btnSolidColor)
        val strokeColor = a.getColor(R.styleable.RoundButton_btnStrokeColor, 0x0)
        val strokeWidth = a.getDimensionPixelSize(R.styleable.RoundButton_btnStrokeWidth, 0)
        val strokeDashWidth = a.getDimensionPixelSize(R.styleable.RoundButton_btnStrokeDashWidth, 0)
        val strokeDashGap = a.getDimensionPixelSize(R.styleable.RoundButton_btnStrokeDashGap, 0)

        a.recycle()

        setSingleLine(true)
        gravity = Gravity.CENTER

        val rd = RoundDrawable(cornerRadius == -1)
        rd.cornerRadius = (if (cornerRadius == -1) 0 else cornerRadius).toFloat()
        rd.setStroke(strokeWidth, strokeColor, strokeDashWidth.toFloat(), strokeDashGap.toFloat())

        if (solidColor == null) {
            solidColor = ColorStateList.valueOf(0)
        }
        if (solidColor!!.isStateful) {
            rd.setSolidColors(solidColor)
        } else if (pressedRatio > 0.0001f) {
            rd.setSolidColors(csl(solidColor.defaultColor, pressedRatio))
        } else {
            rd.setColor(solidColor.defaultColor)
        }
        background = rd
    }

    // 灰度
    internal fun greyer(color: Int): Int {
        val blue = color and 0x000000FF shr 0
        val green = color and 0x0000FF00 shr 8
        val red = color and 0x00FF0000 shr 16
        val grey = Math.round(red * 0.299f + green * 0.587f + blue * 0.114f)
        return Color.argb(0xff, grey, grey, grey)
    }

    // 明度
    internal fun darker(color: Int, ratio: Float): Int {
        var color = color
        color = if (color shr 24 == 0) 0x22808080 else color
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= ratio
        return Color.HSVToColor(color shr 24, hsv)
    }

    internal fun csl(normal: Int, ratio: Float): ColorStateList {
        val pressed = darker(normal, ratio)
        val states = arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf())
        val colors = intArrayOf(pressed, normal)
        return ColorStateList(states, colors)
    }

    private class RoundDrawable(isStadium: Boolean) : GradientDrawable() {
        private var mIsStadium = false

        private var mSolidColors: ColorStateList? = null
        private var mFillColor: Int = 0

        init {
            mIsStadium = isStadium
        }

        fun setSolidColors(colors: ColorStateList) {
            mSolidColors = colors
            setColor(colors.defaultColor)
        }

        override fun onBoundsChange(bounds: Rect) {
            super.onBoundsChange(bounds)
            if (mIsStadium) {
                val rect = RectF(getBounds())
                cornerRadius = (if (rect.height() > rect.width()) rect.width() else rect.height()) / 2
            }
        }

        override fun setColor(argb: Int) {
            mFillColor = argb
            super.setColor(argb)
        }

        override fun onStateChange(stateSet: IntArray): Boolean {
            if (mSolidColors != null) {
                val newColor = mSolidColors!!.getColorForState(stateSet, 0)
                if (mFillColor != newColor) {
                    setColor(newColor)
                    return true
                }
            }
            return false
        }

        override fun isStateful(): Boolean {
            return super.isStateful() || mSolidColors != null && mSolidColors!!.isStateful
        }
    }
}