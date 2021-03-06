package com.aster.app.weather

import android.os.Build
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.local.db.dao.CurrentWeatherDao
import com.aster.app.weather.util.createSampleForecastEntity
import com.aster.app.weather.util.getOrAwaitValue
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class ForecastDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDatabase: DatabaseService
    private lateinit var forecastDao: CurrentWeatherDao

    @Before
    fun setUp() {
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseService::class.java
        )
            .allowMainThreadQueries()
            .build()

        forecastDao = weatherDatabase.currentWeatherDao()
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @Test
    fun insertAndGetForecaseEntityToDb_Success() {
        // When
        forecastDao.insertCurrentWeather(createSampleForecastEntity())

        // Then
        val value = forecastDao.getCurrentWeather().getOrAwaitValue()
        Log.d("Db test", value?.list.toString())
        Assert.assertEquals("Db test" + value?.list.toString(), 1, value?.list?.size)
        Assert.assertEquals("Heavy Rain", value.list?.get(0)?.weatherList?.get(0)?.description)
    }


}
