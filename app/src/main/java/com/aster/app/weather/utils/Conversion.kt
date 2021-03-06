package com.aster.app.weather.utils

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Conversion {


    val CONST_WIND = 2.23694
    fun convertKelvinTocelsius(kelvin: Double): Double = roundOffDecimal(kelvin - 273.15)

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

    //convert the data string yyyy-MM-dd HH:mm:ss to hr AM/PM format
    fun timeConversion(dateStr: String): String {

        // Get date from string
        // Get date from string
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = dateFormatter.parse(dateStr)

        // Get time from date

        // Get time from date
        val timeFormatter = SimpleDateFormat("h a")
        val displayValue = timeFormatter.format(date)
        return displayValue

    }

    //get the DayOfWeek
    fun getDay(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    //convert Wind mile per hr
    fun convertWindMPH(mps: Double): Double = CONST_WIND * mps


}


