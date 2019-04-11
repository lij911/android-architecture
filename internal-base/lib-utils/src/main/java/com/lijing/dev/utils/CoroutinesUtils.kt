package com.lijing.dev.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.plus

object CoroutinesUtils {

    /**
     * 异常处理
     */
    private val loggingExceptionHandler = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()
    }

    public val AppScope = GlobalScope + loggingExceptionHandler
}