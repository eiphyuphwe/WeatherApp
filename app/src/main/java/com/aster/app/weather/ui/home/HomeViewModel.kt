package com.aster.app.weather.ui.home

import androidx.lifecycle.MutableLiveData
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.domain.Usecase
import com.aster.app.weather.domain.usecases.WeatherForecastUsecase

import com.aster.app.weather.ui.base.BaseViewModel
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        private val weatherForecastUsecase: WeatherForecastUsecase
       ):BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val weather: MutableLiveData<Resource<List<ListItem>>> = MutableLiveData()

    fun onWeatherForecastLoading(city:String?)
    {

        weatherForecastUsecase.invoke(Usecase.Input.Single<String>(city!!))
    }

    fun getWeatherForecast()= weatherForecastUsecase.weatherForecastLiveData

    override fun onCreate() {
        onWeatherForecastLoading("Singapore")
    }


}