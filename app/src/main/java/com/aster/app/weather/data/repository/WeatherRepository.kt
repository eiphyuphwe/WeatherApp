package com.aster.app.weather.data.repository

import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val networkService: NetworkService,
                                            private val databaeService: DatabaseService)
{

    fun fetchWeatherForecast(city:String?) : Single<List<ListItem>>
    {
       return networkService.doWeatherForecastCall(city)
               .map { it.list }
    }

    /*fun fectchWeatherForecastFromLocalDb(city:String?): LiveData<WeatherEntity> {
        return databaeService.currentWeatherDao().getCurrentWeather()
    }*/


}