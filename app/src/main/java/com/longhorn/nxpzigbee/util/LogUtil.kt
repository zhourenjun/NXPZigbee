package com.longhorn.nxpzigbee.util

import android.os.Environment
import android.text.TextUtils
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by lighting0675 on 2017/6/10.
 */
object LogUtil {
    val LINE_BREAK = "\r\n"
    private val thread_local_formatter = object : ThreadLocal<ReusableFormatter>() {
        override fun initialValue(): ReusableFormatter {
            return ReusableFormatter()
        }
    }
    var isAndroid = true
    val ROOT = Environment.getExternalStorageDirectory().absolutePath!!// SD卡中的根目录
    val PATH = "/zrj/log"
    var TAG: String? = "zrj" // 自定义Tag的前缀，可以是作者名

    var DEBUG = true
    // 容许打印日志的类型，默认是true，设置为false则不打印
    var allowD = DEBUG
    var allowE = DEBUG
    var allowI = DEBUG
    var allowV = DEBUG
    var allowW = DEBUG
    var allowWtf = DEBUG
    private var PATH_LOG_INFO: String? = null

    init {
       var os: String = System.getProperty("os.name")
        if (os.toLowerCase().contains("win") || os.toLowerCase().contains("mac")) {
            isAndroid = false
        } else {
            PATH_LOG_INFO = ROOT + PATH
            isAndroid = true
        }
    }


    private fun loge(tag: String, content: String, tr: Throwable?) {
        if (isAndroid) {
            android.util.Log.e(tag, content, tr)
        } else {
            System.err.println(tag + "-" + content)
        }
    }

    private fun logd(tag: String, content: String) {
        if (isAndroid) {
            android.util.Log.d(tag, content)
        } else {
            println(tag + "-" + content)
        }
    }

    private fun logw(tag: String, content: String) {
        if (isAndroid) {
            android.util.Log.w(tag, "" + content)
        } else {
            println(tag + "-" + content)
        }
    }

    private fun logi(tag: String, content: String) {
        if (isAndroid) {
            android.util.Log.i(tag, content)
        } else {
            println(tag + "-" + content)
        }
    }

    private fun logv(tag: String, content: String) {
        if (isAndroid) {
            android.util.Log.v(tag, content)
        } else {
            println(tag + "-" + content)
        }
    }

    private fun logwtf(tag: String, tr: Throwable?) {
        if (isAndroid) {
            android.util.Log.wtf(tag, tr)
        } else {
            if (tr != null) {
                println(tag + "-" + tr.message)
            }
        }
    }

    private fun logwtf(tag: String, content: String) {
        if (isAndroid) {
            android.util.Log.wtf(tag, content)
        } else {
            println(tag + "-" + content)
        }
    }

    private fun logwtf(tag: String, content: String, tr: Throwable) {
        if (isAndroid) {
            android.util.Log.wtf(tag, content, tr)
        } else {
            println(tag + "-" + content)
        }
    }

    fun v(content: String) {
        if (!allowV)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logv(tag, content)
    }

    fun v(content: String, isSaveLog: Boolean) {
        if (!allowV)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logv(tag, content)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun v(uTag: String, content: String) {
        if (!allowV) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logv(tag, content)
    }

    fun v(uTag: String, content: String, isSaveLog: Boolean) {
        if (!allowV) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logv(tag, content)

        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun d(content: String) {
        if (!allowD)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logd(tag, content)
    }

    fun d(content: String, isSaveLog: Boolean) {
        if (!allowD)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logd(tag, content)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun d(uTag: String, content: String) {
        if (!allowD) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logd(tag, content)
    }

    fun d(uTag: String, content: String, isSaveLog: Boolean) {
        if (!allowD) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logd(tag, content)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun i(content: String) {
        if (!allowI)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logi(tag, content)
    }

    fun i(content: String, isSaveLog: Boolean) {
        if (!allowI)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logi(tag, content)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun i(uTag: String, content: String) {
        if (!allowI) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logi(tag, content)
    }

    fun i(uTag: String, content: String, isSaveLog: Boolean) {
        if (!allowI) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logi(tag, content)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun w(content: String) {
        if (!allowW)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logw(tag, content)
    }

    fun w(content: String, isSaveLog: Boolean) {
        if (!allowW)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logw(tag, content)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun w(uTag: String, content: String) {
        if (!allowW) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logw(tag, content)
    }

    fun w(uTag: String, content: String, isSaveLog: Boolean) {
        if (!allowW) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        logw(tag, content)

        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun e(content: String) {
        if (!allowE)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        loge(tag, content, null)
    }

    fun e(content: String, isSaveLog: Boolean) {
        if (!allowE)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        loge(tag, content, null)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun e(uTag: String, content: String) {
        if (!allowE) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        loge(tag, content, null)
    }

    fun e(uTag: String, content: String, isSaveLog: Boolean) {
        if (!allowE) {
            return
        }
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        loge(tag, content, null)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun e(content: String, tr: Throwable) {
        if (!allowE)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        android.util.Log.e(tag, content, tr)
    }

    fun e(uTag: String, content: String, tr: Throwable) {
        if (!allowE)
            return
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        android.util.Log.e(tag, content, tr)
    }

    fun e(content: String, tr: Throwable, isSaveLog: Boolean) {
        if (!allowE)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        android.util.Log.e(tag, content, tr)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, getThrowable(tr, content))
        }
    }

    fun e(tr: Throwable, isSaveLog: Boolean) {
        if (!allowE)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        val content = getThrowable(tr, null)
        loge(tag, content, null)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, content)
        }
    }

    fun e(tr: Throwable) {
        if (!allowE)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)

        val content = getThrowable(tr, null)
        loge(tag, content, null)
    }

    fun e(uTag: String, content: String, tr: Throwable, isSaveLog: Boolean) {
        if (!allowE)
            return
        TAG = uTag
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        loge(tag, content, tr)

        val msg = getThrowable(tr, content)
        if (isSaveLog && isAndroid) {
            point(PATH_LOG_INFO!!, tag, msg)
        }
    }

    fun wtf(content: String) {
        if (!allowWtf)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        logwtf(tag, content)
    }

    fun wtf(content: String, tr: Throwable) {
        if (!allowWtf)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        logwtf(tag, content, tr)
    }

    fun wtf(tr: Throwable) {
        if (!allowWtf)
            return
        val caller = getCallerStackTraceElement()
        val tag = generateTag(caller)
        logwtf(tag, tr)
    }

    private fun getCallerStackTraceElement(): StackTraceElement {
        return Thread.currentThread().stackTrace[4]
    }

    fun point(path: String, tag: String, msg: String) {
        var path = path
        if (isSDAva()) {
            val date = Date()
            val dateFormat = SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE)
            dateFormat.applyPattern("yyyy")
            path = path + dateFormat.format(date) + "/"
            dateFormat.applyPattern("MM")
            path += dateFormat.format(date) + "/"
            dateFormat.applyPattern("dd")
            path += dateFormat.format(date) + ".log"
            dateFormat.applyPattern("[yyyy-MM-dd HH:mm:ss]")
            val time = dateFormat.format(date)
            val file = File(path)
            if (!file.exists())
                createDipPath(path)
            var out: BufferedWriter? = null
            try {
                out = BufferedWriter(OutputStreamWriter(FileOutputStream(file, true)))
                out.write("$time $tag $msg\r\n")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    if (out != null) {
                        out.close()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * 根据文件路径 递归创建文件
     * @param file
     */
    fun createDipPath(file: String) {
        val parentFile = file.substring(0, file.lastIndexOf("/"))
        val file1 = File(file)
        val parent = File(parentFile)
        if (!file1.exists()) {
            parent.mkdirs()
            try {
                file1.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun format(msg: String, vararg args: Any): String {
        val formatter = thread_local_formatter.get()
        return formatter.format(msg, *args)
    }

    fun isSDAva(): Boolean {
        return isAndroid && Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) || Environment.getExternalStorageDirectory().exists()
    }

    private fun generateTag(caller: StackTraceElement): String {
        var tag = "(%s:%d).%s" // 占位符
        val callerClazzName = caller.fileName
        tag = String.format(tag, callerClazzName, caller.lineNumber, caller.methodName) // 替换
        tag = if (TAG == null || "".equals(TAG!!, ignoreCase = true))
            tag
        else
            TAG + ":" +tag
        return tag
    }

    private fun getThrowable(throwable: Throwable?, mag: String?): String {
        /* 打印异常 */
        val sb = StringBuilder()
        if (!TextUtils.isEmpty(mag)) {
            sb.append(mag)
        }
        if (throwable != null) {
            sb.append(LINE_BREAK)
            val stringWriter = StringWriter()
            val printWriter = PrintWriter(stringWriter)
            throwable.printStackTrace(printWriter)
            sb.append(stringWriter.toString())
        }
        return sb.toString()
    }

    /**
     * A little trick to reuse a formatter in the same thread
     */
    private class ReusableFormatter {

        private val formatter: Formatter

        private val builder: StringBuilder = StringBuilder()

        init {
            formatter = Formatter(builder)
        }

        fun format(msg: String, vararg args: Any): String {
            formatter.format(msg, args)
            val s = builder.toString()
            builder.setLength(0)
            return s
        }

    }
}