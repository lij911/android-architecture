package com.lijing.dev.utils

object NullObjects {

    fun isNull(obj: Any?): Boolean {
        return obj == null
    }

    fun isNotNull(obj: Any): Boolean {
        return !isNull(obj)
    }

    fun isEmpty(obj: Any): Boolean {
        return if (isNull(obj)) {
            false
        } else (obj as? Collection<*>)?.isEmpty() ?: ((obj as? String)?.isEmpty() ?: true)

    }

    fun isNotEmpty(obj: Any): Boolean {
        return !isEmpty(obj)
    }
}
