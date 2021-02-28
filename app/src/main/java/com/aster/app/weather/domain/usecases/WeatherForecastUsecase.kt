package com.aster.app.weather.domain.usecases

import androidx.lifecycle.LiveData
import com.aster.app.weather.data.local.db.entity.ForecastEntity
import com.aster.app.weather.data.local.db.entity.WeatherEntity
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.domain.Usecase
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.common.Status
import javax.inject.Inject

class WeatherForecastUsecase @Inject constructor(private val weatherForecastRepository: WeatherForecastRepository) :
    Usecase<WeatherForecastUsecase.WeatherForecastInput,WeatherForecastUsecase.WeatherForecastOutput>(){

     data class WeatherForecastInput(val city : String?) : Usecase.Input


     class WeatherForecastOutput() : Usecase.Output<WeatherForecastUsecase.WeatherForecastData> {
     }

    data class WeatherForecastData(val forecastEntity: ForecastEntity)

    override fun executeUsecase(input: WeatherForecastInput): LiveData<Resource<>> {

       var listItemList =  weatherForecastRepository.fetchWeatherForecast(input.city)

    }
}