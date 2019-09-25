package com.spitaliere.data.preferences

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface Preferences {

    fun saveBoolean(key: String, value: Boolean)

    fun saveString(key: String, value: String)

    fun saveInt(key: String, value: Int)

    fun saveLong(key: String, value: Long)

    fun getBoolean(key: String, default: Boolean): Boolean

    fun getString(key: String, default: String): String

    fun getInt(key: String, default: Int): Int

    fun getLong(key: String, default: Long): Long
}