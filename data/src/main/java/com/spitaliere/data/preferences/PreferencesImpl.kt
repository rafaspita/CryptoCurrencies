package com.spitaliere.data.preferences

import android.content.SharedPreferences

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class PreferencesImpl(private val sharedPreferences : SharedPreferences) : Preferences {

    companion object{
        const val LAST_SYNC = "last_sync"
    }

    override fun saveBoolean(key: String, value: Boolean) = sharedPreferences.edit().putBoolean(key, value).apply()

    override fun saveString(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()

    override fun saveInt(key: String, value: Int) = sharedPreferences.edit().putInt(key, value).apply()

    override fun saveLong(key: String, value: Long) = sharedPreferences.edit().putLong(key, value).apply()

    override fun getBoolean(key: String, default: Boolean): Boolean = sharedPreferences.getBoolean(key, default)

    override fun getString(key: String, default: String): String = sharedPreferences.getString(key, default)?: default

    override fun getInt(key: String, default: Int): Int = sharedPreferences.getInt(key, default)

    override fun getLong(key: String, default: Long): Long = sharedPreferences.getLong(key, default)
}