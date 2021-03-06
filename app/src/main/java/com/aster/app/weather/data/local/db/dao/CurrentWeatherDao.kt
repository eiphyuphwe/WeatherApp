package com.aster.app.weather.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aster.app.weather.data.local.db.entity.ForecastEntity


@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM Forecast")
    fun getCurrentWeather(): LiveData<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherEntity: ForecastEntity)

    @Query("Select count(*) from Forecast")
    fun getCount(): Int
}
