package com.angelsit.kudagofeed.data.sharedpreference

import com.angelsit.kudagofeed.App

object SharedPrefHelper {

    private const val EVENT_PREF = "EVENT_PREF"

    private fun getPrefs() = App.getAppContext().getSharedPreferences(EVENT_PREF, 0)

    fun putBoolean(key: String, value: Boolean) = getPrefs().edit().putBoolean(key, value).apply()

    fun getBoolean(key: String, defaultValue: Boolean = false) =
        getPrefs().getBoolean(key, defaultValue)
}