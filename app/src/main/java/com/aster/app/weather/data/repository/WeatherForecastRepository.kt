package com.aster.app.weather.data.repository

import androidx.lifecycle.LiveData
import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.local.db.entity.ForecastEntity
import com.aster.app.weather.data.local.db.entity.WeatherEntity
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.remote.NetworkService
import com.aster.app.weather.data.remote.response.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastRepository @Inject constructor(private val networkService: NetworkService,
                                                    private val databaseService: DatabaseService)
{

    fun fetchWeatherForecast(city:String?) : Single<List<ListItem>>
    {
       return networkService.doWeatherForecastCall(city)
               .map { it.list }
    }

    fun fetchWeatherForecastFromDb(city:String?): LiveData<ForecastEntity> {
        return databaseService.currentWeatherDao().getCurrentWeather()
    }

    fun insertWeatherForecastToDb(list:List<ListItem>)
    {
        databaseService.currentWeatherDao().insertCurrentWeather(ForecastEntity(list))
    }

    fun getAllWeatherForecastFromDb() = databaseService.currentWeatherDao().getCurrentWeather();


}