package com.aster.app.weather.util

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.aster.app.weather.data.local.db.entity.ForecastEntity
import com.aster.app.weather.data.model.*

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}


// Data Generators
fun createSampleForecastEntity(): ForecastEntity {
    val weatherItem = WeatherItem(1, "rains", "Heavy Rain", "a01d_svg")
    val weather = listOf(weatherItem)
    val listItem = ListItem(
        123123, Main(34.0, 30.0, 2.0, 321.0, 21, 132.0, 12.0, 35.0),
        weather, Clouds(1), Wind(12.0, 12.0),
        Rain(0.76),
        Sys("d"),
        10000,
        0.65, "2021-03-06 06:00:00"
    )
    val list = listOf(listItem)
    return ForecastEntity(1, list)
}






