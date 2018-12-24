package com.lijing.dev.storage

import android.content.Context
import android.content.SharedPreferences
import com.lijing.dev.utils.ContextUtils

object SpUtils {

    private const val FILE_DEFAULT_NAME = "DefaultPreference"

    @JvmStatic
    public fun getSharedPreferences(): SharedPreferences? {
        return ContextUtils.getContext()?.getSharedPreferences(FILE_DEFAULT_NAME, Context.MODE_PRIVATE)
    }

    @JvmStatic
    public fun getSharedPreferences(fileName: String): SharedPreferences? {
        return ContextUtils.getContext()?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    @JvmStatic
    public fun put(sharedPreferences: SharedPreferences?, key: String, value: Any) {
        var edit = sharedPreferences?.edit()
        when (value) {
            is Int -> edit?.putInt(key, value)
            is String -> edit?.putString(key, value)
            is Boolean -> edit?.putBoolean(key, value)
            is Float, is Double -> edit?.putFloat(key, value as Float)
            is Long -> edit?.putLong(key, value)
        }
        edit?.apply()
    }

    @JvmStatic
    fun contains(sharedPreferences: SharedPreferences?, key: String): Boolean {
        return sharedPreferences?.contains(key) == true
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T> get(sharedPreferences: SharedPreferences?, key: String, defaultValue: T): T {
        if (!contains(sharedPreferences, key)) return defaultValue
        when (defaultValue) {
            is Int -> return sharedPreferences?.getInt(key, defaultValue) as T
            is String -> return sharedPreferences?.getString(key, defaultValue) as T
            is Boolean -> return sharedPreferences?.getBoolean(key, defaultValue) as T
            is Float -> return sharedPreferences?.getFloat(key, defaultValue) as T
            is Long -> return sharedPreferences?.getLong(key, defaultValue) as T
        }
        return defaultValue
    }
}


