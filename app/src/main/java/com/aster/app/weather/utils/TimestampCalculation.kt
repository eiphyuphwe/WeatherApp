package com.aster.app.weather.utils

import java.util.concurrent.TimeUnit

object TimestampCalculation {


    /**
     * Method to generate timestamp
     */
    fun generateTimestamp(): Long = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

    val TIMESTAMP_STALENESS_THRESHOLD_IN_SECONDS:Long= 3 * 60 * 60

    fun isTimestampStale(timestamp: Long): Boolean {
        val currentTimestamp = generateTimestamp()
        val elpsTime = currentTimestamp - timestamp

        return (elpsTime > TIMESTAMP_STALENESS_THRESHOLD_IN_SECONDS)
    }


}