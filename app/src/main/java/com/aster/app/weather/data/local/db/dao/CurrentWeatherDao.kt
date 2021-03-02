package com.aster.app.weather.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aster.app.weather.data.local.db.entity.ForecastEntity
import com.aster.app.weather.data.local.db.entity.WeatherEntity


@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM Forecast")
    fun getCurrentWeather(): LiveData<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherEntity: ForecastEntity)

    @Transaction
    fun deleteAndInsert(currentWeatherEntity: ForecastEntity) {
        deleteCurrentWeather()
        insertCurrentWeather(currentWeatherEntity)
    }

    @Query("DELETE FROM Forecast")
    fun deleteCurrentWeather()

    @Query("Select count(*) from Forecast")
    fun getCount(): Int
}
