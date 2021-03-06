package com.aster.app.weather

import com.aster.app.weather.utils.Conversion
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.DayOfWeek

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ConversionUnitTest {
    @Test
    fun convertKelvinTocelsius_Success() {
        val temp: Double = 302.86
        val temp_min: Double = 302.3


        val exp_temp: Double = 29.8
        val exp_temp_min: Double = 29.2

        Assert.assertEquals(exp_temp, Conversion.convertKelvinTocelsius(temp), 0.05)
        Assert.assertEquals(exp_temp_min, Conversion.convertKelvinTocelsius(temp_min), 0.05)

    }

    @Test
    fun convertDateStrToTime_Success() {
        val dateStr = "2021-03-06 13:21:22"

        val exp_time = "1 PM"

        Assert.assertEquals(exp_time, Conversion.timeConversion(dateStr))


    }

    @Test
    fun getDayFromDt_Success() {
        val dt: Long = 1615010400

        val expDay = DayOfWeek.SATURDAY

        Assert.assertEquals(expDay, Conversion.getDay(dt))


    }

}
