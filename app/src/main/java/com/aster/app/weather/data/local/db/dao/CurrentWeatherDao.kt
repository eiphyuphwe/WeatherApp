package com.aster.app.weather.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aster.app.weather.data.local.db.entity.WeatherEntity


@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM CurrentWeather")
    fun getCurrentWeather(): LiveData<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherEntity: WeatherEntity)

    @Transaction
    fun deleteAndInsert(currentWeatherEntity: WeatherEntity) {
        deleteCurrentWeather()
        insertCurrentWeather(currentWeatherEntity)
    }

    @Query("DELETE FROM CurrentWeather")
    fun deleteCurrentWeather()

    @Query("Select count(*) from CurrentWeather")
    fun getCount(): Int
}
