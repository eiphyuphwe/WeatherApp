package com.aster.app.weather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aster.app.weather.data.local.db.dao.CurrentWeatherDao
import com.aster.app.weather.data.local.db.dao.DummyDao
import com.aster.app.weather.data.local.db.entity.DummyEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        CurrentWeatherDao::class
    ],
    exportSchema = false,
    version = 1
)
//@TypeConverters(DataConverter::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao
}