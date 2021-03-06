package com.aster.app.weather.data.repository

import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.local.db.entity.ForecastEntity
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.remote.NetworkService
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


    fun insertWeatherForecastToDb(list:List<ListItem>)
    {
        databaseService.currentWeatherDao().insertCurrentWeather(ForecastEntity(list))
    }

    fun getAllWeatherForecastFromDb() = databaseService.currentWeatherDao().getCurrentWeather();


}