package com.aster.app.weather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aster.app.weather.data.local.db.dao.CurrentWeatherDao
import com.aster.app.weather.data.local.db.entity.*
import com.aster.app.weather.utils.DataConverter
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        ForecastEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(DataConverter::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao
}