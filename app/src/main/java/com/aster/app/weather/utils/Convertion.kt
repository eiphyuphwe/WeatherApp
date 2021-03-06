package com.aster.app.weather.utils

import com.aster.app.weather.data.model.Wind
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Convertion {

    fun convertKelvinTocelsius(kelvin: Double): Double = roundOffDecimal(kelvin - 273.15)

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

    val TIMESTAMP_STALENESS_THRESHOLD_IN_SECONDS:Long= 3 * 60 * 60

    fun timeConvertion(dateStr: String) : String
    {

        var convertedTime = ""
        val tk = StringTokenizer(dateStr)
        val date: String = tk.nextToken()
        val time: String = tk.nextToken()

        val sdf = SimpleDateFormat("hh:mm:ss")
        val sdfs = SimpleDateFormat("hh a")
        val dt: Date
        try {
            dt = sdf.parse(time)
            System.out.println("Time Display: " + sdfs.format(dt)) // <-- I got result here
             convertedTime = sdfs.format(dt);
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return convertedTime

    }

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

    fun convertWindMPH(mps: Double) : Double = 2.23694 * mps


}


