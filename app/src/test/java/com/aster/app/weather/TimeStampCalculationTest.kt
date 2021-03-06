package com.aster.app.weather

import com.aster.app.weather.utils.TimestampCalculation
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TimeStampCalculationTest {
    @Test
    fun generateTimeStampTest_Success() {

        val timstamp = TimestampCalculation.generateTimestamp()
        Assert.assertNotNull("Time stamp is null", timstamp)

    }

    @Test
    fun currentTimeStampIsGreaterThanThreeHrs_Success() {
        val timeStamp = TimestampCalculation.TIMESTAMP_STALENESS_THRESHOLD_IN_SECONDS + 120;
        Assert.assertTrue(TimestampCalculation.isTimestampStale(timeStamp))
    }

}
