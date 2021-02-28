package com.aster.app.weather.data.remote

import com.aster.app.weather.data.remote.response.*
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(Endpoints.OPENWEATHERMAP)
    fun doWeatherForecastCall(
        @Query("q") city: String?,
        @Query(Networking.API_KEY) apiKey: String = Networking.API_KEY_VAL
    ): Single<ForecastResponse>


}