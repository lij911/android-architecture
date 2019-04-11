package com.lijing.dev.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.lijing.dev.utils.CoroutinesUtils.AppScope
import kotlinx.coroutines.*
import java.net.URL
import java.util.concurrent.ConcurrentHashMap

/**
 * 简简单单的 bitmap + 协程
 */
object BitmapUtils {

    private val bitmapCache: ConcurrentHashMap<String, Bitmap> = ConcurrentHashMap()

    private val defaultOptions = lazy {
        BitmapFactory.Options().let {
            // do default option
            it.inSampleSize = 2
            it
        }
    }

    /**
     * 添加 suspend 的回调方法将被挂起，直到执行完成后，才执行之后的步骤
     * 在有返回值的回调中非常适用
     */
    fun loadImage(url: String, options: BitmapFactory.Options = defaultOptions.value,
                   callBack: suspend (Bitmap) -> Unit) = AppScope.async(Dispatchers.Default) {
        bitmapCache[url]?.let {
            AppScope.launch(Dispatchers.Main) {
                callBack.invoke(it)
            }
            return@async
        }
        URL(url).openConnection().let {
            it.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            it.setRequestProperty("Connection", "Keep-Alive")
            it.setRequestProperty("Charset", "UTF-8")
            it.doInput = true
            it.doOutput = true
            it.connect()
            it.getInputStream()
        }.let {
            BitmapFactory.decodeStream(it, Rect(), options)?.let { bitmap ->
                AppScope.async(Dispatchers.Main) {
                    callBack.invoke(bitmap)
                }
                bitmapCache[url] = bitmap

            }
            // 在主线程运行
            it.close()
        }
    }

    /**
     * 清空 cache
     */
    fun recycler() {
        bitmapCache.entries.forEach {
            it.value.recycle()
            bitmapCache.remove(it.key)
        }
    }
}