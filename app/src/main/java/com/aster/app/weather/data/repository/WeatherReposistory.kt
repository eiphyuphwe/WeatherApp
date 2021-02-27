package com.aster.app.weather.data.repository

import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.model.Post
import com.aster.app.weather.data.model.WeatherPojo
import com.aster.app.weather.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class WeatherReposistory @Inject constructor(private val networkService: NetworkService,
                                             private val databaeService: DatabaseService)
{

    fun fetchWeatherForecast(city:String?) : Single<List<WeatherPojo>>
    {
       return networkService.doWeatherForecastCall(city)
               .map { it.list }
    }


}