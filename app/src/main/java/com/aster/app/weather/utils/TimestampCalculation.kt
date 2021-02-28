package com.aster.app.weather.utils

import java.util.concurrent.TimeUnit

object TimestampCalculation {

    fun generateTimestamp(): Long = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

    val TIMESTAMP_STALENESS_THRESHOLD_IN_SECONDS:Long= 3 * 60 * 60

    fun isTimestampStale(timestamp: Long): Boolean {
        val currentTimestamp = generateTimestamp()
        return ((currentTimestamp - timestamp) > TIMESTAMP_STALENESS_THRESHOLD_IN_SECONDS)
    }

}