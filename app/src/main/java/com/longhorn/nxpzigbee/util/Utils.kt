package com.longhorn.nxpzigbee.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import java.io.File

/**
 * Created by lighting0675 on 2017/6/30.
 */
object Utils {

    fun getFilePath(filePath: String, fileName: String): File? {
        var file: File? = null
        makeRootDirectory(filePath)
        try {
            file = File(filePath + fileName)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return file
    }

    fun makeRootDirectory(filePath: String) {
        val file: File
        try {
            file = File(filePath)
            if (!file.exists()) {
                file.mkdir()
            }
        } catch (e: Exception) {

        }

    }

    fun getRoundedBitmap(imgUri: String, context: Context): Drawable? {
        val src = BitmapFactory.decodeFile(imgUri)
        if (src == null) {
            return src
        }
        val dst: Bitmap
        if (src.width >= src.height) {
            dst = Bitmap.createBitmap(src, src.width / 2 - src.height / 2, 0, src.height, src.height)
        } else {
            dst = Bitmap.createBitmap(src, 0, src.height / 2 - src.width / 2, src.width, src.width)
        }
        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, dst)
        roundedBitmapDrawable.cornerRadius = (dst.width / 2).toFloat()
        roundedBitmapDrawable.setAntiAlias(true)
        return roundedBitmapDrawable
    }

    fun tintDrawable(drawable: Drawable, colors: ColorStateList): Drawable {
        val wrappedDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTintList(wrappedDrawable, colors)
        return wrappedDrawable
    }

    fun getBinaryString(condition: Int): String {
        var binary = Integer.toBinaryString(condition)
        if (binary.length - 8 < 0) {
            for (i in 1..(8 - binary.length)) {
                binary = "0" + binary
            }
        }
        return binary
    }

    fun bytesToHexString(src: ByteArray): String {
        val stringBuilder = StringBuilder("")

        if (src == null || src.size <= 0) {
            return ""
        }
        for (i in src.indices) {
            val v = src[i].toInt() and 0xFF
            val hv = Integer.toHexString(v)
            if (hv.length < 2) {
                stringBuilder.append(0)
            }
            stringBuilder.append(hv)
        }
        return stringBuilder.toString()
    }

    private fun byteArrayToHex(a: ByteArray): String {
        val sb = StringBuilder(a.size * 2)
        for (b in a)
            sb.append(String.format("%02x", b.toInt() and 0xff))
        return sb.toString()
    }


    /**
     * 十六进制字符串转十进制
     */
    fun hexStringToDecimal(hex: String): Int {
        var hex = hex.toUpperCase()
        val max = hex.length
        var result = 0
        for (i in max downTo 1) {
            val c = hex[i - 1]
            var decimal = 0
            if (c in '0'..'9') {
                decimal = c - '0'
            } else {
                decimal = c.toInt() - 55
            }
            result += (Math.pow(16.0, (max - i).toDouble()) * decimal).toInt()
        }
        return result
    }
}