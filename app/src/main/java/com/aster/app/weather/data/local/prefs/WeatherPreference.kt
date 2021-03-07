package com.aster.app.weather.data.local.prefs


import android.content.SharedPreferences

import javax.inject.Inject

class WeatherPreference @Inject constructor(private val prefs: SharedPreferences) {

    companion object {

        const val NETWORK_CALL_TIMESTAMP_KEY = "network_call_timestamp"
        const val NAME = "settings_pref"
    }

    fun setTimeStamp(timeStamp: Long) {
        prefs.edit().putLong(NETWORK_CALL_TIMESTAMP_KEY, timeStamp).apply()
    }

    fun getTimeStamp(): Long? =
        prefs.getLong(NETWORK_CALL_TIMESTAMP_KEY, 0)


}