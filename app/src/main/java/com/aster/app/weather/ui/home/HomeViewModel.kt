package com.aster.app.weather.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.domain.Usecase
import com.aster.app.weather.domain.usecases.WeatherForecastUsecase
import com.aster.app.weather.ui.base.BaseViewModel
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val weatherForecastUsecase: WeatherForecastUsecase
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val weather: MutableLiveData<Resource<List<ListItem>>> = MutableLiveData()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onWeatherForecastLoading(city: String?) {

        //invoke weatherForecastUsecase business domain, usecase will decide to fetch the data from whether from network or db
        weatherForecastUsecase.invoke(Usecase.Input.Single<String>(city!!))
    }

    fun getWeatherForecast() = weatherForecastUsecase.weatherForecastLiveDataMap


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        onWeatherForecastLoading("Singapore")
    }


}