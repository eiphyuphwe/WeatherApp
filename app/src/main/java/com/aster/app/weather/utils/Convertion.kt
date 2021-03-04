package com.aster.app.weather.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

object Convertion {

    fun convertKelvinTocelsius(kelvin:Double): Double = roundOffDecimal(kelvin - 273.15)

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

    val TIMESTAMP_STALENESS_THRESHOLD_IN_SECONDS:Long= 3 * 60 * 60


}