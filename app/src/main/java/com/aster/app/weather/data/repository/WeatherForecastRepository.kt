package com.aster.app.weather.data.repository

import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.local.db.entity.ForecastEntity
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    //fetch the data from network
    fun fetchWeatherForecast(city: String?): Single<List<ListItem>> {
        return networkService.doWeatherForecastCall(city)
            .map { it.list }
    }


    //insert the weather forecast listItem to db
    fun insertWeatherForecastToDb(list: List<ListItem>) {
        databaseService.currentWeatherDao().insertCurrentWeather(ForecastEntity(list))
    }

    //get the weatherForecast Live data from db
    fun getAllWeatherForecastFromDb() = databaseService.currentWeatherDao().getCurrentWeather();


}